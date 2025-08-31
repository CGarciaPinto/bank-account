package com.example.bank.controller;

import com.example.bank.model.CompteBancaire;
import com.example.bank.model.ReleveCompte;
import com.example.bank.service.CompteBancaireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class CompteBancaireController {

    private final CompteBancaireService compteBancaireService;

    public CompteBancaireController(CompteBancaireService compteBancaireService) {
        this.compteBancaireService = compteBancaireService;
    }

    @PostMapping
    public CompteBancaire creerCompteBancaire() {
        return compteBancaireService.creerCompteBancaire();
    }

    @GetMapping
    public List<CompteBancaire> getAllComptesBancaires() {
        return compteBancaireService.getAllComptesBancaires();
    }

    @PutMapping("/{id}/depot")
    public CompteBancaire deposerArgent(@PathVariable Long id, @RequestParam double montant) {
        return compteBancaireService.deposerArgent(id, montant);
    }

    @PutMapping("/{id}/retrait")
    public CompteBancaire retirerArgent(@PathVariable Long id, @RequestParam double montant) {
        return compteBancaireService.retirerArgent(id, montant);
    }

    @GetMapping("/{id}/releve")
    public ReleveCompte getReleveCompte(@PathVariable Long id) {
        return compteBancaireService.getReleveCompte(id);
    }

}
