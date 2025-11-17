package com.example.bank.model;

import com.example.bank.model.enums.TypeCompteEnum;
import com.example.bank.model.enums.TypeLivretEnum;
import org.junit.jupiter.api.Test;

import static com.example.bank.model.enums.TypeCompteEnum.LIVRET_EPARGNE;
import static org.assertj.core.api.Assertions.assertThat;

class ReleveCompteTest {

    @Test
    void constructor_withCompteCourant_shouldInitializeCorrectly() {
        CompteBancaire compte = new CompteBancaire(500);
        compte.deposerArgent(100, "test");
        ReleveCompte releve = new ReleveCompte(compte);

        assertThat(releve.getTypeCompte()).isEqualTo(TypeCompteEnum.COMPTE_COURANT);
        assertThat(releve.getSoldeActuel()).isEqualTo(compte.getSolde());
        assertThat(releve.getOperations()).hasSize(1);
        assertThat(releve.getCompteBancaire()).isEqualTo(compte);
    }

    @Test
    void constructor_withLivretEpargne_shouldInitializeCorrectly() {
        LivretEpargne livret = new LivretEpargne(TypeLivretEnum.LIVRET_A);
        livret.deposerArgent(200, "test");
        ReleveCompte releve = new ReleveCompte(livret);

        assertThat(releve.getTypeCompte()).isEqualTo(TypeCompteEnum.LIVRET_EPARGNE);
        assertThat(releve.getSoldeActuel()).isEqualTo(livret.getSolde());
        assertThat(releve.getOperations()).hasSize(1);
        assertThat(LIVRET_EPARGNE.getTypeCompte().equals(releve.getTypeCompte().getTypeCompte()));
        assertThat(releve.getCompteBancaire()).isEqualTo(livret);
    }

    @Test
    void toString_withNoOperations_shouldReturnReadableMessage() {
        CompteBancaire compte = new CompteBancaire(500);
        ReleveCompte releve = new ReleveCompte(compte);

        String s = releve.toString();
        assertThat(s).contains("=== Relevé de Compte ===");
        assertThat(s).contains("Type de compte : COMPTE COURANT");
        assertThat(s).contains("Solde actuel   : 500.0 €");
        assertThat(s).contains("(Aucune opération ce mois-ci)");
    }

    @Test
    void toString_withOperations_shouldReturnReadableList() {
        CompteBancaire compte = new CompteBancaire(0);
        compte.deposerArgent(100, "test");
        ReleveCompte releve = new ReleveCompte(compte);

        String s = releve.toString();
        assertThat(s).contains("=== Relevé de Compte ===");
        assertThat(s).contains("Type de compte : COMPTE COURANT");
        assertThat(s).contains("Solde actuel   : 100.0 €");
        assertThat(s).contains("Opérations du dernier mois :");
        assertThat(s).contains("DÉPÔT");
        assertThat(s).contains("montant: 100.0 €");
        assertThat(s).contains("solde après opération: 100.0 €");
    }
    
}