package com.example.bank.exception;

public class CompteNotFoundException extends RuntimeException {
    public CompteNotFoundException(Long idCompte) {
      super("Compte bancaire avec l'identifiant " + idCompte + " introuvable.");
    }
}
