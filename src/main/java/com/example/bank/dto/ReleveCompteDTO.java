package com.example.bank.dto;

import com.example.bank.model.CompteBancaire;
import com.example.bank.model.enums.TypeCompteEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReleveCompteDTO {

    private TypeCompteEnum typeCompte;
    private double soldeActuel;
    private CompteBancaire compteBancaire;
    private List<OperationDTO> operations;
}
