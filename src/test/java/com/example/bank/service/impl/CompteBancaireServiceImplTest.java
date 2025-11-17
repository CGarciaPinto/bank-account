package com.example.bank.service.impl;

import com.example.bank.dto.CompteBancaireDTO;
import com.example.bank.dto.OperationRequestDTO;
import com.example.bank.exception.CompteNotFoundException;
import com.example.bank.model.CompteBancaire;
import com.example.bank.repository.CompteBancaireRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompteBancaireServiceImplTest {

    @Mock
    private CompteBancaireRepository compteBancaireRepository;

    @InjectMocks
    private CompteBancaireServiceImpl compteBancaireService;

    @Test
    void creerCompteBancaire_shouldReturnCompteDTO() {
        CompteBancaire compte = new CompteBancaire();
        when(compteBancaireRepository.save(any(CompteBancaire.class))).thenReturn(compte);

        CompteBancaireDTO dto = compteBancaireService.creerCompteBancaire();

        assertThat(dto).isNotNull();
        assertThat(dto.getIdCompte()).isNull();
        verify(compteBancaireRepository, times(1)).save(any(CompteBancaire.class));
    }

    @Test
    void getCompteBancaireById_withValidId_shouldReturnCompte() {
        CompteBancaire compte = new CompteBancaire(100.0, 50.0);
        when(compteBancaireRepository.findById(1L)).thenReturn(Optional.of(compte));

        CompteBancaireDTO dto = compteBancaireService.getCompteBancaireById(1L);

        assertThat(dto).isNotNull();
        assertThat(dto.getSolde()).isEqualTo(100.0);
        verify(compteBancaireRepository).findById(1L);
    }

    @Test
    void getCompteBancaireById_withInvalidId_shouldThrowCompteNotFoundException() {
        when(compteBancaireRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> compteBancaireService.getCompteBancaireById(1L))
                .isInstanceOf(CompteNotFoundException.class)
                .hasMessageContaining("1");
    }

    @Test
    void getAllComptesBancaires_shouldReturnSortedList() {
        CompteBancaire compte1 = new CompteBancaire(200.0, 50.0);
        CompteBancaire compte2 = new CompteBancaire(100.0, 20.0);

        ReflectionTestUtils.setField(compte1, "idCompte", 2L);
        ReflectionTestUtils.setField(compte2, "idCompte", 1L);

        when(compteBancaireRepository.findAll()).thenReturn(Arrays.asList(compte1, compte2));

        List<CompteBancaireDTO> result = compteBancaireService.getAllComptesBancaires();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getIdCompte()).isEqualTo(1L);
        assertThat(result.get(1).getIdCompte()).isEqualTo(2L);
    }

    @Test
    void deposerArgent_withValidId_shouldIncreaseSolde() {
        CompteBancaire compte = new CompteBancaire(100.0, 50.0);
        when(compteBancaireRepository.findById(1L)).thenReturn(Optional.of(compte));
        when(compteBancaireRepository.save(compte)).thenReturn(compte);

        OperationRequestDTO operationRequestDTO = new OperationRequestDTO(50.0, "test");

        CompteBancaireDTO dto = compteBancaireService.deposerArgent(1L, operationRequestDTO);

        assertThat(dto.getSolde()).isEqualTo(150.0);
        verify(compteBancaireRepository).save(compte);
    }

    @Test
    void retirerArgent_withValidId_shouldDecreaseSolde() {
        CompteBancaire compte = new CompteBancaire(200.0, 50.0);
        when(compteBancaireRepository.findById(1L)).thenReturn(Optional.of(compte));
        when(compteBancaireRepository.save(compte)).thenReturn(compte);

        OperationRequestDTO operationRequestDTO = new OperationRequestDTO(50.0, "test");

        CompteBancaireDTO dto = compteBancaireService.retirerArgent(1L, operationRequestDTO);

        assertThat(dto.getSolde()).isEqualTo(150.0);
        verify(compteBancaireRepository).save(compte);
    }

    @Test
    void getInfoReleveCompte_withValidId_shouldReturnReleveCompte() {
        CompteBancaire compte = new CompteBancaire(300.0, 0.0);
        when(compteBancaireRepository.findById(1L)).thenReturn(Optional.of(compte));

        String releve = compteBancaireService.getInfoReleveCompte(1L);

        assertThat(releve).contains("Relevé de Compte");
        assertThat(releve).contains("Solde actuel");
    }

    @Test
    void getCompteBancaireById_withUnknownId_shouldThrowCompteNotFoundException() {
        // given
        Long id = 99L;
        when(compteBancaireRepository.findById(id)).thenReturn(Optional.empty());

        // when + then
        assertThrows(CompteNotFoundException.class, () -> compteBancaireService.getCompteBancaireById(id));
    }

    @Test
    void getInfoReleveCompte_withUnknownId_shouldThrowCompteNotFoundException() {
        // given
        Long id = 7L;
        when(compteBancaireRepository.findById(id)).thenReturn(Optional.empty());

        // when + then
        assertThrows(CompteNotFoundException.class, () -> compteBancaireService.getInfoReleveCompte(id));
    }

    @Test
    void deposerArgent_withUnknownId_shouldThrowCompteNotFoundException() {
        // given
        Long id = 123L;
        OperationRequestDTO operationRequestDTO = new OperationRequestDTO(200.0, "test");
        when(compteBancaireRepository.findById(id)).thenReturn(Optional.empty());

        // when + then
        assertThrows(CompteNotFoundException.class,
                () -> compteBancaireService.deposerArgent(id, operationRequestDTO));
    }

    @Test
    void retirerArgent_withUnknownId_shouldThrowCompteNotFoundException() {
        // given
        Long id = 456L;
        OperationRequestDTO operationRequestDTO = new OperationRequestDTO(150.0, "test");
        when(compteBancaireRepository.findById(id)).thenReturn(Optional.empty());

        // when + then
        assertThrows(CompteNotFoundException.class,
                () -> compteBancaireService.retirerArgent(id, operationRequestDTO));
    }
}