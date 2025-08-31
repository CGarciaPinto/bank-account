package com.example.bank.service;

import com.example.bank.dto.CompteBancaireDTO;
import com.example.bank.model.CompteBancaire;

import java.util.List;

public interface CompteBancaireService {

    CompteBancaireDTO creerCompteBancaire();

    CompteBancaireDTO getCompteBancaireById(Long idCompte);

    List<CompteBancaireDTO> getAllComptesBancaires();

    CompteBancaireDTO deposerArgent(Long id, double montant);

    CompteBancaireDTO retirerArgent(Long id, double montant);

    String getInfoReleveCompte(Long id);

}
