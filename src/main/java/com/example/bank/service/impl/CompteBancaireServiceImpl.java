package com.example.bank.service.impl;

import com.example.bank.dto.CompteBancaireDTO;
import com.example.bank.exception.CompteNotFoundException;
import com.example.bank.mapper.CompteBancaireMapper;
import com.example.bank.model.CompteBancaire;
import com.example.bank.model.ReleveCompte;
import com.example.bank.repository.CompteBancaireRepository;
import com.example.bank.service.CompteBancaireService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompteBancaireServiceImpl implements CompteBancaireService {

    private final CompteBancaireRepository compteBancaireRepository;

    public CompteBancaireServiceImpl(CompteBancaireRepository compteBancaireRepository) {
        this.compteBancaireRepository = compteBancaireRepository;
    }

    @Override
    public CompteBancaireDTO creerCompteBancaire() {
        CompteBancaire compteBancaire = new CompteBancaire();
        CompteBancaire nouvelleCompte = compteBancaireRepository.save(compteBancaire);
        return CompteBancaireMapper.toDTO(nouvelleCompte);
    }

    @Override
    public CompteBancaireDTO getCompteBancaireById(Long idCompte) {
        CompteBancaire compteBancaire = compteBancaireRepository.findById(idCompte)
                .orElseThrow(() -> new CompteNotFoundException(idCompte));
        return CompteBancaireMapper.toDTO(compteBancaire);
    }

    @Override
    public List<CompteBancaireDTO> getAllComptesBancaires() {
        return compteBancaireRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(CompteBancaire::getId))
                .map(CompteBancaireMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CompteBancaireDTO deposerArgent(Long id, double montant) {
        CompteBancaire compteBancaire = compteBancaireRepository.findById(id)
                .orElseThrow(() -> new CompteNotFoundException(id));
        compteBancaire.deposerArgent(montant);
        CompteBancaire compteBancaireMAJ = compteBancaireRepository.save(compteBancaire);
        return CompteBancaireMapper.toDTO(compteBancaireMAJ);
    }

    @Override
    public CompteBancaireDTO retirerArgent(Long id, double montant) {
        CompteBancaire compteBancaire = compteBancaireRepository.findById(id)
                .orElseThrow(() -> new CompteNotFoundException(id));
        compteBancaire.retirerArgent(montant);
        CompteBancaire compteBancaireMAJ = compteBancaireRepository.save(compteBancaire);
        return CompteBancaireMapper.toDTO(compteBancaireMAJ);
    }

    @Override
    public String getInfoReleveCompte(Long id) {
        CompteBancaire compte = compteBancaireRepository.findById(id)
                .orElseThrow(() -> new CompteNotFoundException(id));
        return new ReleveCompte(compte).toString();
    }

}
