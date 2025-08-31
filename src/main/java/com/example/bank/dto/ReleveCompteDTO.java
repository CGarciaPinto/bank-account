package com.example.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReleveCompteDTO {

    private String numeroDeCompte;
    private List<OperationDTO> operations;
}
