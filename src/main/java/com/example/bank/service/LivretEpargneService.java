package com.example.bank.service;

import com.example.bank.dto.LivretEpargneDTO;
import com.example.bank.model.enums.TypeLivretEnum;

import java.util.List;

public interface LivretEpargneService {

    LivretEpargneDTO creerLivretEpargne(TypeLivretEnum typeLivret);

    List<LivretEpargneDTO> getAllLivretsEpargnes();
}
