package com.example.bank.service;

import com.example.bank.model.LivretEpargne;
import com.example.bank.model.enums.TypeLivretEnum;

import java.util.List;

public interface LivretEpargneService {

    LivretEpargne creerLivretEpargne(TypeLivretEnum typeLivret);

    List<LivretEpargne> getAllLivretsEpargnes();
}
