package com.example.bank.model;

import java.util.UUID;

public class BankAccount {

    private final String numeroDeCompte;
    private double solde;

    public String getNumeroDeCompte() {
        return numeroDeCompte;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public BankAccount(double solde) {
        this.numeroDeCompte = UUID.randomUUID().toString();
        this.solde = solde;
    }

    public BankAccount(){
        this.numeroDeCompte = UUID.randomUUID().toString();
        this.solde = 0;
    }

    public void deposerArgent(double montant) {
        if(montant<=0) {
            throw new IllegalArgumentException("Le montant du dépôt doit être supérieur à 0.");
        }
        this.solde += montant;
    }

    public void retirerArgent(double montant) {
        if(montant<=0) {
            throw new IllegalArgumentException("Le montant du retrait doit être supérieur à 0.");
        }
        if (montant > this.solde) {
            throw new IllegalArgumentException("Fonds insuffisants.");
        }
        this.solde-=montant;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + numeroDeCompte + '\'' +
                ", balance=" + solde +
                '}';
    }

}
