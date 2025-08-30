package com.example.bank.model;

import com.example.bank.model.enums.TypeOperationEnum;

import java.time.LocalDateTime;

public class Operation {
    private final LocalDateTime date;
    private final TypeOperationEnum type;
    private final double montant;
    private final double soldeApresOperation;

    public Operation(TypeOperationEnum type, double montant, double soldeApresOperation) {
        this.date = LocalDateTime.now();
        this.type = type;
        this.montant = montant;
        this.soldeApresOperation = soldeApresOperation;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public TypeOperationEnum getType() {
        return type;
    }

    public double getMontant() {
        return montant;
    }

    public double getSoldeApresOperation() {
        return soldeApresOperation;
    }

    @Override
    public String toString() {
        return "[" + date + "] " + type.getOperation() + " : " + montant + " (solde: " + soldeApresOperation + ")";
    }
}
