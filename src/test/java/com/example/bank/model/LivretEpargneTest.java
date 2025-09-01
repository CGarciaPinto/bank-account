package com.example.bank.model;

import com.example.bank.exception.OperationNotAllowedException;
import com.example.bank.model.enums.TypeLivretEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LivretEpargneTest {

    @Test
    void testDeposerArgentDepotValide() {
        LivretEpargne livretEpargne = new LivretEpargne(2000.0, TypeLivretEnum.LIVRET_A);
        livretEpargne.deposerArgent(18000.0);
        assertEquals(20000.0, livretEpargne.getSolde());
    }

    @Test
    void testDeposerArgentDepotDepassePlafond() {
        LivretEpargne livretEpargne = new LivretEpargne(25000.0, TypeLivretEnum.LIVRET_A);

        OperationNotAllowedException exception = assertThrows(
                OperationNotAllowedException.class,
                () -> livretEpargne.deposerArgent(20000.0)
        );

        assertEquals("Dépôt impossible : le solde final dépasse le plafond du livret A (22950.0).", exception.getMessage());
    }

    @Test
    void testDeposerArgentMontantNegatifOuZero() {
        LivretEpargne livretEpargne = new LivretEpargne(25000.0, TypeLivretEnum.LIVRET_A);

        OperationNotAllowedException exception = assertThrows(
                OperationNotAllowedException.class,
                () -> livretEpargne.deposerArgent(0)
        );

        assertEquals("Le montant du dépôt doit être supérieur à 0.", exception.getMessage());
    }

    @Test
    void testRetirerArgentRetraitValide() {
        LivretEpargne livretEpargne = new LivretEpargne(2000.0, TypeLivretEnum.LIVRET_A);
        livretEpargne.retirerArgent(1500.0);
        assertEquals(500.0, livretEpargne.getSolde());
    }

    @Test
    void testRetirerArgentMontantNegatifOuZero() {
        LivretEpargne livretEpargne = new LivretEpargne(2000.0, TypeLivretEnum.LIVRET_A);

        OperationNotAllowedException exception = assertThrows(
                OperationNotAllowedException.class,
                () -> livretEpargne.retirerArgent(-10)
        );

        assertEquals("Le montant du retrait doit être supérieur à 0.", exception.getMessage());
    }

    @Test
    void testRetirerArgentRetraitImpossibleDecouvert() {
        LivretEpargne livretEpargne = new LivretEpargne(1000, TypeLivretEnum.LIVRET_A);

        OperationNotAllowedException exception = assertThrows(
                OperationNotAllowedException.class,
                () -> livretEpargne.retirerArgent(1500)
        );

        assertEquals(
                "Retrait impossible : le livret ne peut pas avoir de découvert.",
                exception.getMessage()
        );
    }

    @Test
    void TestConstructeurGettersSettersToString() {
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
