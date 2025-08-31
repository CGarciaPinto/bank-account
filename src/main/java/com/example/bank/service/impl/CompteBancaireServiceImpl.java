package com.example.bank.service.impl;

import com.example.bank.model.CompteBancaire;
import com.example.bank.model.ReleveCompte;
import com.example.bank.repository.CompteBancaireRepository;
import com.example.bank.service.CompteBancaireService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteBancaireServiceImpl implements CompteBancaireService {

    private final CompteBancaireRepository compteBancaireRepository;

    public CompteBancaireServiceImpl(CompteBancaireRepository compteBancaireRepository) {
        this.compteBancaireRepository = compteBancaireRepository;
    }

    @Override
    public CompteBancaire creerCompteBancaire() {
        CompteBancaire compteBancaire = new CompteBancaire();
        return compteBancaireRepository.save(compteBancaire);
    }

    @Override
    public List<CompteBancaire> getAllComptesBancaires() {
        return compteBancaireRepository.findAll();
    }

    @Override
    public CompteBancaire deposerArgent(Long id, double montant) {
        CompteBancaire compte = compteBancaireRepository.findById(id).orElseThrow(() -> new RuntimeException("Compte introuvable"));
        compte.deposerArgent(montant);
        return compteBancaireRepository.save(compte);
    }

    @Override
    public CompteBancaire retirerArgent(Long id, double montant) {
        CompteBancaire compte = compteBancaireRepository.findById(id).orElseThrow(() -> new RuntimeException("Compte introuvable"));
        compte.retirerArgent(montant);
        return compteBancaireRepository.save(compte);
    }

    @Override
    public String getInfoReleveCompte(Long id) {
        CompteBancaire compte = compteBancaireRepository.findById(id).orElseThrow(() -> new RuntimeException("Compte introuvable"));
        return new ReleveCompte(compte).toString();
    }

}
