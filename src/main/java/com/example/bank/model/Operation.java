package com.example.bank.model;

import com.example.bank.model.enums.TypeOperationEnum;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "operation")
@Getter
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

    @Column(name = "solde_apres_operation", nullable = false)
    private double soldeApresOperation;

    @ManyToOne
    @JoinColumn(name = "compte_id", nullable = false)
    private CompteBancaire compteBancaire;

    public Operation() {}

    public Operation(TypeOperationEnum typeOperation, double montant, double soldeApresOperation, CompteBancaire compteBancaire) {
        this.date = LocalDateTime.now();
        this.typeOperation = typeOperation;
        this.montant = montant;
        this.soldeApresOperation = soldeApresOperation;
        this.compteBancaire = compteBancaire;
    }

    @Override
    public String toString() {
        return "[" + date + "] " + typeOperation.getOperation() + " : " + montant + " (solde: " + soldeApresOperation + ")";
    }
}
