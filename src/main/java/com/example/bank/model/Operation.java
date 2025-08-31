package com.example.bank.model;

import com.example.bank.model.enums.TypeOperationEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idOperation;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeOperationEnum typeOperation;

    @Column(name = "montant", nullable = false)
    private double montant;

    private double soldeApresOperation;

    @ManyToOne
    @JoinColumn(name = "compte_id")
    private CompteBancaire compteBancaire;

    public Operation() {}

    public Operation(TypeOperationEnum typeOperation, double montant, double soldeApresOperation, CompteBancaire compteBancaire) {
        this.date = LocalDateTime.now();
        this.typeOperation = typeOperation;
        this.montant = montant;
        this.soldeApresOperation = soldeApresOperation;
        this.compteBancaire = compteBancaire;
    }

    public Long getId() {
        return idOperation;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public TypeOperationEnum getType() {
        return typeOperation;
    }

    public double getMontant() {
        return montant;
    }

    public double getSoldeApresOperation() {
        return soldeApresOperation;
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    @Override
    public String toString() {
        return "[" + date + "] " + typeOperation.getOperation() + " : " + montant + " (solde: " + soldeApresOperation + ")";
    }
}
