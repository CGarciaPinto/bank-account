package com.example.bank.controller;

import com.example.bank.model.LivretEpargne;
import com.example.bank.model.enums.TypeLivretEnum;
import com.example.bank.service.LivretEpargneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes/livrets")
public class LivretEpargneController {

    private final LivretEpargneService livretEpargneService;

    public LivretEpargneController (LivretEpargneService livretEpargneService) {
        this.livretEpargneService = livretEpargneService;
    }

    @PostMapping
    public LivretEpargne creerLivretEpargne(@RequestParam TypeLivretEnum typeLivret) {
        return livretEpargneService.creerLivretEpargne(typeLivret);
    }

    @GetMapping
    public List<LivretEpargne> getAllComptesBancaires() {
        return livretEpargneService.getAllLivretsEpargnes();
    }
}
