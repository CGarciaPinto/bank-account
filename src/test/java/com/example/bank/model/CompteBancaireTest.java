package com.example.bank.model;

import com.example.bank.exception.OperationNotAllowedException;
import com.example.bank.model.enums.TypeOperationEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class CompteBancaireTest {

    @Test
    void deposerArgent_withValidAmount_shouldIncreaseSolde() {
        CompteBancaire compte = new CompteBancaire();
        compte.deposerArgent(100);
        assertEquals(100, compte.getSolde());
    }

    @Test
    void deposerArgent_withZeroOrNegativeAmount_shouldThrowOperationNotAllowedException() {
        CompteBancaire compte = new CompteBancaire();

        OperationNotAllowedException exception = assertThrows(
                OperationNotAllowedException.class,
                () -> compte.deposerArgent(0)
        );

        assertEquals("Le montant du dépôt doit être supérieur à 0.", exception.getMessage());
    }

    @Test
    void retirerArgent_withValidAmount_shouldDecreaseSolde() {
        CompteBancaire compte = new CompteBancaire(200);
        compte.retirerArgent(50);
        assertEquals(150, compte.getSolde());
    }

    @Test
    void retirerArgent_withZeroOrNegativeAmount_shouldThrowOperationNotAllowedException() {
        CompteBancaire compte = new CompteBancaire(100);

        OperationNotAllowedException exception = assertThrows(
                OperationNotAllowedException.class,
                () -> compte.retirerArgent(-10)
        );

        assertEquals("Le montant du retrait doit être supérieur à 0.", exception.getMessage());
    }

    @Test
    void retirerArgent_withInsufficientFunds_shouldThrowOperationNotAllowedException() {
        CompteBancaire compte = new CompteBancaire(50); //decouvertMax = 0

        OperationNotAllowedException exception = assertThrows(
                OperationNotAllowedException.class,
                () -> compte.retirerArgent(100)
        );

        assertEquals(
                "Retrait impossible : dépasse l'autorisation de découvert (maximum découvert : 0.0).", exception.getMessage());
    }

    @Test
    void retirerArgent_withOverdraftWithinLimit_shouldAllowWithdrawal() {
        CompteBancaire compte = new CompteBancaire(50, 200);
        compte.retirerArgent(200);
        assertEquals(-150.0, compte.getSolde());
    }

    @Test
    void toString_shouldContainRelevantAccountInformation() {
        CompteBancaire compte = new CompteBancaire(500.0, 100.0);
        String resultat = compte.toString();

        assertTrue(resultat.contains("Compte bancaire"));
        assertTrue(resultat.contains("solde=500.0"));
        assertTrue(resultat.contains("decouvertMax=100.0"));
        assertTrue(resultat.contains("numeroDeCompte="));

        System.out.println(resultat);
    }

    @Test
    void deposerArgentAndRetirerArgent_shouldUpdateSoldeAndRegisterOperations() {
        CompteBancaire compteD = new CompteBancaire(100, 50);
        CompteBancaire compteR = new CompteBancaire(100, 50);

        compteD.deposerArgent(200);
        compteR.retirerArgent(50);

        assertThat(compteD.getSolde()).isEqualTo(300);
        assertThat(compteD.getOperations().get(0).getTypeOperation())
                .isEqualTo(TypeOperationEnum.DEPOT);

        assertThat(compteR.getSolde()).isEqualTo(50);
        assertThat(compteR.getOperations().get(0).getTypeOperation())
                .isEqualTo(TypeOperationEnum.RETRAIT);
    }

    @Test
    void gettersAndSetters_shouldWorkAsExpected() {
        CompteBancaire compte = new CompteBancaire();

        compte.setDecouvertMax(500.0);

        assertThat(compte.getDecouvertMax()).isEqualTo(500.0);
        assertThat(compte.getIdCompte()).isNull();
        assertThat(compte.getNumeroDeCompte()).isNotNull();
        assertThat(compte.getDecouvertMax()).isEqualTo(500.0);
    }
}
