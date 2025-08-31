package com.example.bank.mapper;

import com.example.bank.dto.CompteBancaireDTO;
import com.example.bank.model.CompteBancaire;

import java.util.stream.Collectors;

public class CompteBancaireMapper {

    public static CompteBancaireDTO toDTO(CompteBancaire compte) {
        CompteBancaireDTO dto = new CompteBancaireDTO();
        dto.setId(compte.getId());
        dto.setNumeroDeCompte(compte.getNumeroDeCompte());
        dto.setSolde(compte.getSolde());
        dto.setDecouvertMax(compte.getDecouvertMax());

        if (compte.getOperations() != null) {
            dto.setOperations(
                    compte.getOperations()
                            .stream()
                            .map(OperationMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
}
