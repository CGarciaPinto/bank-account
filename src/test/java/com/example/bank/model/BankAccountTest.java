package com.example.bank.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void testDeposerArgent() {
        BankAccount compte = new BankAccount();
        compte.deposerArgent(100);
        assertEquals(100, compte.getSolde());
    }

    @Test
    void testDeposerArgentMontantNegatifOuZero() {
        BankAccount compte = new BankAccount();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> compte.deposerArgent(0)
        );

        assertEquals("Le montant du dépôt doit être supérieur à 0.", exception.getMessage());
    }

    @Test
    void testRetirerArgent() {
        BankAccount compte = new BankAccount(200);
        compte.retirerArgent(50);
        assertEquals(150, compte.getSolde());
    }

    @Test
    void testRetirerArgentMontantNegatifOuZero() {
        BankAccount compte = new BankAccount(100);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> compte.retirerArgent(-10)
        );

        assertEquals("Le montant du retrait doit être supérieur à 0.", exception.getMessage());
    }

    @Test
    void testRetirerArgentFondsInsuffisants() {
        BankAccount compte = new BankAccount(50); //decouvertMax = 0

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> compte.retirerArgent(100)
        );

        assertEquals(
                "Retrait impossible : dépasse l'autorisation de découvert (maximum découvert : 0.0).", exception.getMessage());
    }

    @Test
    void testRetirerArgentFondsSuffisants() {
        BankAccount compte = new BankAccount(50, 200);
        compte.retirerArgent(200);
        assertEquals(-150.0, compte.getSolde());
    }

    @Test
    void testToString() {
        BankAccount compte = new BankAccount(500.0, 100.0);
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
