package com.example.bank.model;

import com.example.bank.model.enums.TypeOperationEnum;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OperationTest {

    @Test
    void testConstructeurEtGetters() {
        CompteBancaire compte = new CompteBancaire(100, 50);
        double soldeApresOp = 150;
        double montant = 50;

        Operation operation = new Operation(TypeOperationEnum.DEPOT, montant, soldeApresOp, compte);

        assertThat(operation.getIdOperation()).isNull();
        assertThat(operation.getDate()).isNotNull();
        assertThat(operation.getMontant()).isEqualTo(montant);
        assertThat(operation.getSoldeApresOperation()).isEqualTo(soldeApresOp);
        assertThat(operation.getCompteBancaire()).isEqualTo(compte);
        assertThat(operation.getTypeOperation()).isEqualTo(TypeOperationEnum.DEPOT);
    }

    @Test
    void testToString() {
        CompteBancaire compte = new CompteBancaire(200, 100);
        Operation operation = new Operation(TypeOperationEnum.DEPOT, 75, 275, compte);

        String resultat = operation.toString();

        assertThat(resultat).contains("75");
        assertThat(resultat).contains("275.0");
        assertThat(resultat).contains("DÉPÔT");
    }

}
