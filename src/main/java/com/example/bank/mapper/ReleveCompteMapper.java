package com.example.bank.mapper;

import com.example.bank.dto.ReleveCompteDTO;
import com.example.bank.model.ReleveCompte;

import java.util.stream.Collectors;

public class ReleveCompteMapper {

    public static ReleveCompteDTO toDTO(ReleveCompte releveCompte) {
        ReleveCompteDTO dto = new ReleveCompteDTO();
        dto.setNumeroDeCompte(releveCompte.getCompteBancaire().getNumeroDeCompte());
        dto.setOperations(
                releveCompte.getCompteBancaire().getOperations()
                        .stream()
                        .map(OperationMapper::toDTO)
                        .collect(Collectors.toList())
        );
        return dto;
    }
}
