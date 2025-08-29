package com.example.bank.model;

import com.example.bank.model.enums.TypeLivretEnum;

public class LivretEpargne extends BankAccount {

    private final TypeLivretEnum typeLivret;

    public LivretEpargne(double solde, TypeLivretEnum typeLivret) {
        super(solde, 0); // Un livret d'épargne ne peut pas avoir de découvert
        this.typeLivret = typeLivret;
    }

    public TypeLivretEnum getTypeLivret() {
        return typeLivret;
    }

    @Override
    public void deposerArgent(double montant) {
        if(montant<=0) {
            throw new IllegalArgumentException("Le montant du dépôt doit être supérieur à 0.");
        }

        double soldeFinal = super.getSolde() + montant;
        if (soldeFinal >= typeLivret.getPlafondDepot()) {
            throw new IllegalArgumentException(
                    "Dépôt impossible : le solde final dépasse le plafond du livret " +
                            typeLivret.getCode() + " (" + typeLivret.getPlafondDepot() + ")."
            );
        }
        super.setSolde(soldeFinal);
    }

    @Override
    public void retirerArgent(double montant) {
        if(montant<=0) {
            throw new IllegalArgumentException("Le montant du retrait doit être supérieur à 0.");
        }

        double soldeFinal = super.getSolde() - montant;

        if (soldeFinal < 0) {
            throw new IllegalArgumentException("Retrait impossible : le livret ne peut pas avoir de découvert.");
        }

        super.setSolde(soldeFinal);
    }

    @Override
    public String toString() {
        return "LivretEpargne{" +
                "numeroDeCompte='" + getNumeroDeCompte() + '\'' +
                ", solde=" + getSolde() +
                ", typeLivret=" + typeLivret.getCode() +
                ", plafondDepot=" + typeLivret.getPlafondDepot() +
                '}';
    }
}
