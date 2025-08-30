package com.example.bank.model.enums;

public enum TypeCompteEnum {
    LIVRET_EPARGNE("LIVRET ÉPARGNE"),
    COMPTE_COURANT("COMPTE COURANT");

    private final String typeCompte;

    TypeCompteEnum(String typeCompte) {
        this.typeCompte = typeCompte;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    @Override
    public String toString() {
        return typeCompte;
    }
}
