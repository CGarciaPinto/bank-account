package com.example.bank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationRequestDTO {
    private double montant;
    private String description;

    public OperationRequestDTO() {}

    public OperationRequestDTO(double montant, String description) {
        this.montant = montant;
        this.description = description;
    }
}
