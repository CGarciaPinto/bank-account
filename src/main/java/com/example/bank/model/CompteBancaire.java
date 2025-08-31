package com.example.bank.model;

import com.example.bank.model.enums.TypeOperationEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "compte_bancaire")
@Getter
public class CompteBancaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idCompte; // PK

    @Column(name = "numero_de_compte", nullable = false, updatable = false, unique = true)
    private final String numeroDeCompte;

    @Column(name = "solde", nullable = false)
    @Setter
    private double solde;

    @Column(name = "decouvert_max", nullable = false)
    @Setter
    private double decouvertMax;

    @OneToMany(mappedBy = "compteBancaire", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<Operation> operations = new ArrayList<>();

    public CompteBancaire(double solde, double decouvertMax) {
        this.numeroDeCompte = UUID.randomUUID().toString();
        this.solde = solde;
        this.decouvertMax = decouvertMax;
    }

    public CompteBancaire(double solde) {
        this(solde, 0.0);
    }

    public CompteBancaire(){
        this(0.0, 0.0);
    }

    public Long getId() {
        return idCompte;
    }

    public List<Operation> getOperations() {
        return List.copyOf(operations);
    }

    public void deposerArgent(double montant) {
        if(montant<=0) {
            throw new IllegalArgumentException("Le montant du dépôt doit être supérieur à 0.");
        }
        solde += montant;
        enregistrerOperation(TypeOperationEnum.DEPOT, montant, solde);
    }

    public void retirerArgent(double montant) {
        if(montant<=0) {
            throw new IllegalArgumentException("Le montant du retrait doit être supérieur à 0.");
        }

        double soldeFinal = solde - montant;

        if (soldeFinal < -decouvertMax) {
            throw new IllegalArgumentException("Retrait impossible : dépasse l'autorisation de découvert" +
                    " (maximum découvert : " + decouvertMax + ").");
        }
        solde=soldeFinal;
        enregistrerOperation(TypeOperationEnum.RETRAIT, montant, soldeFinal);
    }

    protected void enregistrerOperation(TypeOperationEnum type, double montant, double soldeFinal) {
        operations.add(new Operation(type, montant, soldeFinal, this));
    }

    @Override
    public String toString() {
        return "Compte bancaire{" +
                "numeroDeCompte='" + numeroDeCompte + '\'' +
                ", solde=" + solde +
                ", decouvertMax=" + decouvertMax +
                '}';
    }

}
