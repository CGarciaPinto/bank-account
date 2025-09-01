package com.example.bank.mapper;

import com.example.bank.dto.LivretEpargneDTO;
import com.example.bank.model.LivretEpargne;

import java.util.stream.Collectors;

public class LivretEpargneMapper {

    public static LivretEpargneDTO toDTO(LivretEpargne livret) {
        LivretEpargneDTO dto = new LivretEpargneDTO();
        dto.setId(livret.getIdCompte());
        dto.setNumeroDeCompte(livret.getNumeroDeCompte());
        dto.setSolde(livret.getSolde());
        dto.setDecouvertMax(livret.getDecouvertMax());
        dto.setTypeLivret(livret.getTypeLivret());

        if (livret.getOperations() != null) {
            dto.setOperations(
                    livret.getOperations()
                            .stream()
                            .map(OperationMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

}
