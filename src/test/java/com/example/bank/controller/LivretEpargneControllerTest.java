package com.example.bank.controller;

import com.example.bank.dto.LivretEpargneDTO;
import com.example.bank.model.enums.TypeLivretEnum;
import com.example.bank.service.LivretEpargneService;
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
class LivretEpargneControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LivretEpargneService livretEpargneService;

    @InjectMocks
    private LivretEpargneController livretEpargneController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(livretEpargneController).build();
    }

    @Test
    void creerLivretEpargne_shouldReturnStatusOk() throws Exception {
        TypeLivretEnum typeLivret = TypeLivretEnum.LIVRET_A;
        LivretEpargneDTO dto = new LivretEpargneDTO();
        when(livretEpargneService.creerLivretEpargne(typeLivret)).thenReturn(dto);

        mockMvc.perform(post("/api/comptes/livrets")
                        .param("typeLivret", typeLivret.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllLivretsEpargnes_shouldReturnStatusOk() throws Exception {
        LivretEpargneDTO dto = new LivretEpargneDTO();
        when(livretEpargneService.getAllLivretsEpargnes()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/comptes/livrets")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
