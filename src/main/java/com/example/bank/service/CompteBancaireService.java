package com.example.bank.service;

import com.example.bank.model.CompteBancaire;
import com.example.bank.model.ReleveCompte;

import java.util.List;

public interface CompteBancaireService {

    CompteBancaire creerCompteBancaire();

    List<CompteBancaire> getAllComptesBancaires();

    CompteBancaire deposerArgent(Long id, double montant);

    CompteBancaire retirerArgent(Long id, double montant);

    ReleveCompte getReleveCompte(Long id);

}
