package com.example.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiRootController {

    @GetMapping
    public String accueil() {
        return "Bienvenue dans l'API BankAccount!";
    }
}
