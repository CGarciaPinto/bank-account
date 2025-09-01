package com.example.bank.mapper;

import com.example.bank.dto.OperationDTO;
import com.example.bank.model.CompteBancaire;
import com.example.bank.model.Operation;
import com.example.bank.model.enums.TypeOperationEnum;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class OperationMapperTest {

    @Test
    void testToDTO() {
        CompteBancaire compteBancaire = new CompteBancaire();
        Operation operation = new Operation(TypeOperationEnum.DEPOT, 200, 500, compteBancaire);

        OperationDTO dto = OperationMapper.toDTO(operation);

        assertThat(dto.getIdOperation()).isNull();
        assertThat(dto.getTypeOperation().equals(TypeOperationEnum.DEPOT));
        assertThat(dto.getDate()).isNotNull();
        assertThat(dto.getMontant()).isEqualTo(200L);
        assertThat(dto.getSoldeApresOperation()).isEqualTo(500L);
    }
}
