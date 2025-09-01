package com.example.bank.model;

import com.example.bank.exception.OperationNotAllowedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompteBancaireTest {

    @Test
    void testDeposerArgentValide() {
        CompteBancaire compte = new CompteBancaire();
        compte.deposerArgent(100);
        assertEquals(100, compte.getSolde());
    }

    @Test
    void testDeposerArgentMontantNegatifOuZero() {
        CompteBancaire compte = new CompteBancaire();

        OperationNotAllowedException exception = assertThrows(
                OperationNotAllowedException.class,
                () -> compte.deposerArgent(0)
        );

        assertEquals("Le montant du dépôt doit être supérieur à 0.", exception.getMessage());
    }

    @Test
    void testRetirerArgentValide() {
        CompteBancaire compte = new CompteBancaire(200);
        compte.retirerArgent(50);
        assertEquals(150, compte.getSolde());
    }

    @Test
    void testRetirerArgentMontantNegatifOuZero() {
        CompteBancaire compte = new CompteBancaire(100);

        OperationNotAllowedException exception = assertThrows(
                OperationNotAllowedException.class,
                () -> compte.retirerArgent(-10)
        );

        assertEquals("Le montant du retrait doit être supérieur à 0.", exception.getMessage());
    }

    @Test
    void testRetirerArgentFondsInsuffisants() {
        CompteBancaire compte = new CompteBancaire(50); //decouvertMax = 0

        OperationNotAllowedException exception = assertThrows(
                OperationNotAllowedException.class,
                () -> compte.retirerArgent(100)
        );

        assertEquals(
                "Retrait impossible : dépasse l'autorisation de découvert (maximum découvert : 0.0).", exception.getMessage());
    }

    @Test
    void testRetirerArgentFondsSuffisants() {
        CompteBancaire compte = new CompteBancaire(50, 200);
        compte.retirerArgent(200);
        assertEquals(-150.0, compte.getSolde());
    }

    @Test
    void testToString() {
        CompteBancaire compte = new CompteBancaire(500.0, 100.0);
        String resultat = compte.toString();

        // Aseguramos que el toString contiene los campos importantes
        assertTrue(resultat.contains("Compte bancaire"));
        assertTrue(resultat.contains("solde=500.0"));
        assertTrue(resultat.contains("decouvertMax=100.0"));
        assertTrue(resultat.contains("numeroDeCompte="));

        // Imprimimos para inspección manual si queremos
        System.out.println(resultat);
    }

}
