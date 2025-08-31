package com.example.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompteBancaireDTO {

    private Long id;
    private String numeroDeCompte;
    private double solde;
    private double decouvertMax;
    private List<OperationDTO> operations;

}
