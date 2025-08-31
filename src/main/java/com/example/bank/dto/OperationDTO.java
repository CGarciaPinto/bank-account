package com.example.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OperationDTO {

    private LocalDateTime date;
    private double montant;
    private double soldeApresOperation;
}
