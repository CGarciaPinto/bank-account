package com.example.bank.mapper;

import com.example.bank.dto.OperationDTO;
import com.example.bank.model.Operation;

public class OperationMapper {

    public static OperationDTO toDTO(Operation operation) {
        OperationDTO dto = new OperationDTO();
        dto.setDate(operation.getDate());
        dto.setMontant(operation.getMontant());
        dto.setSoldeApresOperation(operation.getSoldeApresOperation());
        return dto;
    }
}

