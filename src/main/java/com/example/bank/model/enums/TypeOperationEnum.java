package com.example.bank.model.enums;

public enum TypeOperationEnum {
    DEPOT("DÉPÔT"),
    RETRAIT("RETRAIT");

    private final String operation;

    TypeOperationEnum(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        return operation;
    }
}
