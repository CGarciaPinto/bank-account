package com.example.bank.dto;

import com.example.bank.model.enums.TypeOperationEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OperationDTO {

    private Long idOperation;
    private LocalDateTime date;
    private TypeOperationEnum typeOperation;
    private double montant;
    private double soldeApresOperation;
}
