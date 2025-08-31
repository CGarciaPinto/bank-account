package com.example.bank.model;

import com.example.bank.model.enums.TypeLivretEnum;
import com.example.bank.model.enums.TypeOperationEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "livret_epargne")
public class LivretEpargne extends CompteBancaire {

    @Enumerated(EnumType.STRING)
    @Column(name = "type_livret", nullable = false)
    private TypeLivretEnum typeLivret;

    // Constructeur vide pour JPA
    public LivretEpargne() {
        super();
    }

    public LivretEpargne(double solde, TypeLivretEnum typeLivret) {
        super(solde, 0); // Un livret d'épargne ne peut pas avoir de découvert
        this.typeLivret = typeLivret;
    }

    public LivretEpargne(TypeLivretEnum typeLivret) {
        super(0,0); // Un livret d'épargne ne peut pas avoir de découvert
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
        super.enregistrerOperation(TypeOperationEnum.DEPOT, montant, soldeFinal);
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
        super.enregistrerOperation(TypeOperationEnum.RETRAIT, montant, soldeFinal);
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
