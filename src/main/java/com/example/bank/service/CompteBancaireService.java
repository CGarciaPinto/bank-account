package com.example.bank.service;

import com.example.bank.dto.CompteBancaireDTO;
import com.example.bank.dto.OperationRequestDTO;

import java.util.List;

public interface CompteBancaireService {

    CompteBancaireDTO creerCompteBancaire();

    CompteBancaireDTO getCompteBancaireById(Long idCompte);

    List<CompteBancaireDTO> getAllComptesBancaires();

    CompteBancaireDTO deposerArgent(Long id, OperationRequestDTO operationRequestDTO);

    CompteBancaireDTO retirerArgent(Long id, OperationRequestDTO operationRequestDTO);

    String getInfoReleveCompte(Long id);

}
