package com.example.bank.service.impl;

import com.example.bank.dto.LivretEpargneDTO;
import com.example.bank.exception.InvalidParameterException;
import com.example.bank.model.enums.TypeLivretEnum;
import com.example.bank.model.LivretEpargne;
import com.example.bank.repository.LivretEpargneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivretEpargneServiceImplTest {

    @Mock
    private LivretEpargneRepository livretEpargneRepository;

    @InjectMocks
    private LivretEpargneServiceImpl livretEpargneService;

    @Test
    void creerLivretEpargne_withNullType_shouldThrowInvalidParameterException() {
        assertThrows(InvalidParameterException.class,
                () -> livretEpargneService.creerLivretEpargne(null));
    }

    @Test
    void creerLivretEpargne_withValidType_shouldSaveAndReturnDTO() {
        TypeLivretEnum typeLivret = TypeLivretEnum.LIVRET_A;
        LivretEpargne livret = new LivretEpargne(typeLivret);

        when(livretEpargneRepository.save(any(LivretEpargne.class))).thenReturn(livret);

        LivretEpargneDTO dto = livretEpargneService.creerLivretEpargne(typeLivret);

        assertThat(dto).isNotNull();
        assertThat(dto.getTypeLivret()).isEqualTo(typeLivret);
        verify(livretEpargneRepository, times(1)).save(any(LivretEpargne.class));
    }

    @Test
    void getAllLivretsEpargnes_withMultipleLivrets_shouldReturnSortedDTOList() {
        LivretEpargne livret1 = new LivretEpargne(TypeLivretEnum.LIVRET_A);
        LivretEpargne livret2 = new LivretEpargne(TypeLivretEnum.LIVRET_A);

        // Simular IDs con ReflectionTestUtils para ordenar
        ReflectionTestUtils.setField(livret1, "idCompte", 2L);
        ReflectionTestUtils.setField(livret2, "idCompte", 1L);

        when(livretEpargneRepository.findAll()).thenReturn(Arrays.asList(livret1, livret2));

        List<LivretEpargneDTO> result = livretEpargneService.getAllLivretsEpargnes();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getIdCompte()).isEqualTo(1L);
        assertThat(result.get(1).getIdCompte()).isEqualTo(2L);
        verify(livretEpargneRepository, times(1)).findAll();
    }

}
