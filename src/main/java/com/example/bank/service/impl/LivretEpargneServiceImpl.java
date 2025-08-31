package com.example.bank.service.impl;

import com.example.bank.model.LivretEpargne;
import com.example.bank.model.enums.TypeLivretEnum;
import com.example.bank.repository.LivretEpargneRepository;
import com.example.bank.service.LivretEpargneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivretEpargneServiceImpl implements LivretEpargneService {

    private final LivretEpargneRepository livretEpargneRepository;

    public LivretEpargneServiceImpl(LivretEpargneRepository livretEpargneRepository) {
        this.livretEpargneRepository = livretEpargneRepository;
    }

    @Override
    public LivretEpargne creerLivretEpargne(TypeLivretEnum typeLivret) {
        return livretEpargneRepository.save(new LivretEpargne(typeLivret));
    }

    @Override
    public List<LivretEpargne> getAllLivretsEpargnes() {
        return livretEpargneRepository.findAll();
    }

}
