package com.example.bank.mapper;

import com.example.bank.dto.ReleveCompteDTO;
import com.example.bank.model.CompteBancaire;
import com.example.bank.model.ReleveCompte;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReleveCompteMapperTest {

    @Test
    void testToDTO() {
        CompteBancaire compteBancaire = new CompteBancaire(500, 200);
        compteBancaire.deposerArgent(500L, "test");

        ReleveCompte releveCompte = new ReleveCompte(compteBancaire);

        ReleveCompteDTO dto = ReleveCompteMapper.toDTO(releveCompte);

        assertThat(dto.getCompteBancaire()).isEqualTo(releveCompte.getCompteBancaire());
        assertThat(dto.getTypeCompte()).isEqualTo(releveCompte.getTypeCompte());
        assertThat(dto.getSoldeActuel()).isEqualTo(1000L);
        assertThat(dto.getOperations()).hasSize(1);
    }

}
