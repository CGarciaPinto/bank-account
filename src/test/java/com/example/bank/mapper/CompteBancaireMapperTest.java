package com.example.bank.mapper;

import com.example.bank.dto.CompteBancaireDTO;
import com.example.bank.model.CompteBancaire;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CompteBancaireMapperTest {

    @Test
    void testToDTO() {
        CompteBancaire compte = new CompteBancaire(500.0, 200.0);
        compte.deposerArgent(200, "test");

        CompteBancaireDTO dto = CompteBancaireMapper.toDTO(compte);

        assertThat(dto.getIdCompte()).isNull();
        assertThat(dto.getNumeroDeCompte()).isNotNull();
        assertThat(dto.getSolde()).isEqualTo(700.0);
        assertThat(dto.getDecouvertMax()).isEqualTo(200.0);
        assertThat(dto.getOperations()).hasSize(1);
    }
}