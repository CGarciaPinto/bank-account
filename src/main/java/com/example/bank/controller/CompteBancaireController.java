package com.example.bank.controller;

import com.example.bank.dto.CompteBancaireDTO;
import com.example.bank.dto.OperationRequestDTO;
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
    public CompteBancaireDTO creerCompteBancaire() {
        return compteBancaireService.creerCompteBancaire();
    }

    @GetMapping("/{id}")
    public CompteBancaireDTO getCompteBancaireById(@PathVariable Long id) {
        return compteBancaireService.getCompteBancaireById(id);
    }

    @GetMapping
    public List<CompteBancaireDTO> getAllComptesBancaires() {
        return compteBancaireService.getAllComptesBancaires();
    }

    @PutMapping("/{id}/depot")
    public CompteBancaireDTO deposerArgent(@PathVariable Long id, @RequestBody OperationRequestDTO operationRequestDTO) {
        return compteBancaireService.deposerArgent(id, operationRequestDTO);
    }

    @PutMapping("/{id}/retrait")
    public CompteBancaireDTO retirerArgent(@PathVariable Long id, @RequestBody OperationRequestDTO operationRequestDTO) {
        return compteBancaireService.retirerArgent(id, operationRequestDTO);
    }

    @GetMapping("/{id}/releve")
    public String getInfoReleveCompte(@PathVariable Long id) {
        return compteBancaireService.getInfoReleveCompte(id);
    }

}
