package com.example.bank.controller;

import com.example.bank.dto.CompteBancaireDTO;
import com.example.bank.service.CompteBancaireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CompteBancaireControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CompteBancaireService compteBancaireService;

    @InjectMocks
    private CompteBancaireController compteBancaireController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(compteBancaireController).build();
    }

    @Test
    void creerCompteBancaire_shouldReturnStatusOk() throws Exception {
        CompteBancaireDTO dto = new CompteBancaireDTO();
        when(compteBancaireService.creerCompteBancaire()).thenReturn(dto);

        mockMvc.perform(post("/api/comptes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCompteBancaireById_shouldReturnStatusOk() throws Exception {
        Long id = 1L;
        CompteBancaireDTO dto = new CompteBancaireDTO();
        when(compteBancaireService.getCompteBancaireById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/comptes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllComptesBancaires_shouldReturnStatusOk() throws Exception {
        CompteBancaireDTO dto = new CompteBancaireDTO();
        when(compteBancaireService.getAllComptesBancaires()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/comptes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deposerArgent_shouldReturnStatusOk() throws Exception {
        Long id = 1L;
        double montant = 100.0;
        CompteBancaireDTO dto = new CompteBancaireDTO();
        when(compteBancaireService.deposerArgent(id, montant)).thenReturn(dto);

        mockMvc.perform(put("/api/comptes/{id}/depot", id)
                        .param("montant", String.valueOf(montant))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void retirerArgent_shouldReturnStatusOk() throws Exception {
        Long id = 1L;
        double montant = 50.0;
        CompteBancaireDTO dto = new CompteBancaireDTO();
        when(compteBancaireService.retirerArgent(id, montant)).thenReturn(dto);

        mockMvc.perform(put("/api/comptes/{id}/retrait", id)
                        .param("montant", String.valueOf(montant))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getInfoReleveCompte_shouldReturnStatusOk() throws Exception {
        Long id = 1L;
        String releve = "Relevé test";
        when(compteBancaireService.getInfoReleveCompte(id)).thenReturn(releve);

        mockMvc.perform(get("/api/comptes/{id}/releve", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
