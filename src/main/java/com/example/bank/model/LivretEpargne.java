package com.example.bank.model;

import com.example.bank.exception.OperationNotAllowedException;
import com.example.bank.model.enums.TypeLivretEnum;
import com.example.bank.model.enums.TypeOperationEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "livret_epargne")
@NoArgsConstructor // Constructeur vide pour JPA
@Getter
@Setter
public class LivretEpargne extends CompteBancaire {

    @Enumerated(EnumType.STRING)
    @Column(name = "type_livret", nullable = false)
    private TypeLivretEnum typeLivret;

    public LivretEpargne(double solde, TypeLivretEnum typeLivret) {
        super(solde, 0); // Un livret d'épargne ne peut pas avoir de découvert
        this.typeLivret = typeLivret;
    }

    public LivretEpargne(TypeLivretEnum typeLivret) {
        super(0,0); // Un livret d'épargne ne peut pas avoir de découvert
        this.typeLivret = typeLivret;
    }

    @Override
    public void deposerArgent(double montant, String description) {
        if(montant<=0) {
            throw new OperationNotAllowedException("Le montant du dépôt doit être supérieur à 0.");
        }

        double soldeFinal = super.getSolde() + montant;
        if (soldeFinal >= typeLivret.getPlafondDepot()) {
            throw new OperationNotAllowedException(
                    "Dépôt impossible : le solde final dépasse le plafond du livret " +
                            typeLivret.getCode() + " (" + typeLivret.getPlafondDepot() + ")."
            );
        }
        super.setSolde(soldeFinal);
        super.enregistrerOperation(TypeOperationEnum.DEPOT, montant, soldeFinal, description);
    }

    @Override
    public void retirerArgent(double montant, String description) {
        if(montant<=0) {
            throw new OperationNotAllowedException("Le montant du retrait doit être supérieur à 0.");
        }

        double soldeFinal = super.getSolde() - montant;

        if (soldeFinal < 0) {
            throw new OperationNotAllowedException("Retrait impossible : le livret ne peut pas avoir de découvert.");
        }

        super.setSolde(soldeFinal);
        super.enregistrerOperation(TypeOperationEnum.RETRAIT, montant, soldeFinal, description);
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
