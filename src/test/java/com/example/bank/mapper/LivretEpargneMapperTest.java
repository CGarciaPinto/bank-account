package com.example.bank.mapper;

import com.example.bank.dto.LivretEpargneDTO;
import com.example.bank.model.LivretEpargne;
import com.example.bank.model.enums.TypeLivretEnum;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LivretEpargneMapperTest {

    @Test
    void testToDTO() {
        LivretEpargne livret = new LivretEpargne(TypeLivretEnum.LIVRET_A);
        livret.deposerArgent(200, "test");

        LivretEpargneDTO dto = LivretEpargneMapper.toDTO(livret);

        assertThat(dto.getIdCompte()).isNull();
        assertThat(dto.getNumeroDeCompte()).isNotNull();
        assertThat(dto.getSolde()).isEqualTo(200.0);
        assertThat(dto.getDecouvertMax()).isEqualTo(0.0);
        assertThat(dto.getTypeLivret()).isEqualTo(TypeLivretEnum.LIVRET_A);
        assertThat(dto.getOperations()).hasSize(1);
    }
}

