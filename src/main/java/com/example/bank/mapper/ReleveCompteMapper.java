package com.example.bank.mapper;

import com.example.bank.dto.ReleveCompteDTO;
import com.example.bank.model.ReleveCompte;

import java.util.stream.Collectors;

public class ReleveCompteMapper {

    public static ReleveCompteDTO toDTO(ReleveCompte releveCompte) {
        ReleveCompteDTO dto = new ReleveCompteDTO();
        dto.setTypeCompte(releveCompte.getTypeCompte());
        dto.setSoldeActuel(releveCompte.getSoldeActuel());
        dto.setCompteBancaire(releveCompte.getCompteBancaire());
        dto.setOperations(
                releveCompte.getCompteBancaire().getOperations()
                        .stream()
                        .map(OperationMapper::toDTO)
                        .collect(Collectors.toList())
        );
        return dto;
    }
}
