package com.example.bank.service.impl;

import com.example.bank.dto.LivretEpargneDTO;
import com.example.bank.mapper.LivretEpargneMapper;
import com.example.bank.model.LivretEpargne;
import com.example.bank.model.enums.TypeLivretEnum;
import com.example.bank.repository.LivretEpargneRepository;
import com.example.bank.service.LivretEpargneService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivretEpargneServiceImpl implements LivretEpargneService {

    private final LivretEpargneRepository livretEpargneRepository;

    public LivretEpargneServiceImpl(LivretEpargneRepository livretEpargneRepository) {
        this.livretEpargneRepository = livretEpargneRepository;
    }

    @Override
    public LivretEpargneDTO creerLivretEpargne(TypeLivretEnum typeLivret) {
        LivretEpargne livretEpargne = new LivretEpargne(typeLivret);
        return LivretEpargneMapper.toDTO(livretEpargneRepository.save(livretEpargne));
    }

    @Override
    public List<LivretEpargneDTO> getAllLivretsEpargnes() {
        return livretEpargneRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(LivretEpargne::getId))
                .map(LivretEpargneMapper::toDTO)
                .collect(Collectors.toList());
    }

}
