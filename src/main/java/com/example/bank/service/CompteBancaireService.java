package com.example.bank.service;

import com.example.bank.model.CompteBancaire;

import java.util.List;

public interface CompteBancaireService {

    CompteBancaire creerCompteBancaire();

    CompteBancaire getCompteBancaireById(Long idCompte);

    List<CompteBancaire> getAllComptesBancaires();

    CompteBancaire deposerArgent(Long id, double montant);

    CompteBancaire retirerArgent(Long id, double montant);

    String getInfoReleveCompte(Long id);

}
