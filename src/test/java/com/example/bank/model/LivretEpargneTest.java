package com.example.bank.model;

import com.example.bank.exception.OperationNotAllowedException;
import com.example.bank.model.enums.TypeLivretEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LivretEpargneTest {

    @Test
    void deposerArgent_withValidAmount_shouldIncreaseSolde() {
        LivretEpargne livretEpargne = new LivretEpargne(2000.0, TypeLivretEnum.LIVRET_A);
        livretEpargne.deposerArgent(18000.0, "test");
        assertEquals(20000.0, livretEpargne.getSolde());
    }

    @Test
    void deposerArgent_whenExceedingPlafond_shouldThrowOperationNotAllowedException() {
        LivretEpargne livretEpargne = new LivretEpargne(25000.0, TypeLivretEnum.LIVRET_A);

        OperationNotAllowedException exception = assertThrows(
                OperationNotAllowedException.class,
                () -> livretEpargne.deposerArgent(20000.0, "test")
        );

        assertEquals("Dépôt impossible : le solde final dépasse le plafond du livret A (22950.0).", exception.getMessage());
    }

    @Test
    void deposerArgent_withZeroOrNegativeAmount_shouldThrowOperationNotAllowedException() {
        LivretEpargne livretEpargne = new LivretEpargne(25000.0, TypeLivretEnum.LIVRET_A);

        OperationNotAllowedException exception = assertThrows(
                OperationNotAllowedException.class,
                () -> livretEpargne.deposerArgent(0, "test")
        );

        assertEquals("Le montant du dépôt doit être supérieur à 0.", exception.getMessage());
    }

    @Test
    void retirerArgent_withValidAmount_shouldDecreaseSolde() {
        LivretEpargne livretEpargne = new LivretEpargne(2000.0, TypeLivretEnum.LIVRET_A);
        livretEpargne.retirerArgent(1500.0, "test");
        assertEquals(500.0, livretEpargne.getSolde());
    }

    @Test
    void retirerArgent_withZeroOrNegativeAmount_shouldThrowOperationNotAllowedException() {
        LivretEpargne livretEpargne = new LivretEpargne(2000.0, TypeLivretEnum.LIVRET_A);

        OperationNotAllowedException exception = assertThrows(
                OperationNotAllowedException.class,
                () -> livretEpargne.retirerArgent(-10, "test")
        );

        assertEquals("Le montant du retrait doit être supérieur à 0.", exception.getMessage());
    }

    @Test
    void retirerArgent_whenExceedingSolde_shouldThrowOperationNotAllowedException() {
        LivretEpargne livretEpargne = new LivretEpargne(1000, TypeLivretEnum.LIVRET_A);

        OperationNotAllowedException exception = assertThrows(
                OperationNotAllowedException.class,
                () -> livretEpargne.retirerArgent(1500, "test")
        );

        assertEquals(
                "Retrait impossible : le livret ne peut pas avoir de découvert.",
                exception.getMessage()
        );
    }

    @Test
    void constructorAndGettersSettersAndToString_shouldWorkAsExpected() {
        LivretEpargne livret = new LivretEpargne(TypeLivretEnum.LIVRET_A);

        assertThat(livret.getTypeLivret()).isEqualTo(TypeLivretEnum.LIVRET_A);
        assertThat(livret.getSolde()).isEqualTo(0);
        assertThat(livret.getDecouvertMax()).isEqualTo(0);

        livret.setTypeLivret(TypeLivretEnum.LIVRET_B);
        assertThat(livret.getTypeLivret()).isEqualTo(TypeLivretEnum.LIVRET_B);

        String representation = livret.toString();
        assertThat(representation).contains("LivretEpargne");
        assertThat(representation).contains("typeLivret=B");
        assertThat(representation).contains("solde=0");
        assertThat(representation).contains("plafondDepot=" + TypeLivretEnum.LIVRET_B.getPlafondDepot());
    }

}
