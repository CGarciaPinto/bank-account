package com.example.bank.model;

import com.example.bank.model.enums.TypeOperationEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CompteBancaire {

    private final String numeroDeCompte;
    private double solde;
    private double decouvertMax;

    private final List<Operation> operations = new ArrayList<>();

    public String getNumeroDeCompte() {
        return numeroDeCompte;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public double getDecouvertMax() {
        return decouvertMax;
    }

    public void setDecouvertMax(double decouvertMax) {
        this.decouvertMax = decouvertMax;
    }

    public List<Operation> getOperations() {
        return List.copyOf(operations);
    }


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

    public void deposerArgent(double montant) {
        if(montant<=0) {
            throw new IllegalArgumentException("Le montant du dépôt doit être supérieur à 0.");
        }
        double soldeFinal = solde + montant;
        solde = soldeFinal;
        enregistrerOperation(TypeOperationEnum.DEPOT, montant, soldeFinal);
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
        operations.add(new Operation(type, montant, soldeFinal));
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
