package com.example.bank.model;

import com.example.bank.model.enums.TypeCompteEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.bank.model.enums.TypeCompteEnum.COMPTE_COURANT;
import static com.example.bank.model.enums.TypeCompteEnum.LIVRET_EPARGNE;

public class ReleveCompte {
    private final TypeCompteEnum typeCompte;
    private final double soldeActuel;
    private final List<Operation> operations;

    public ReleveCompte(CompteBancaire compte) {
        this.typeCompte = compte instanceof LivretEpargne ? LIVRET_EPARGNE : COMPTE_COURANT;
        this.soldeActuel = compte.getSolde();

        LocalDateTime unMoisAvant = LocalDateTime.now().minusMonths(1);

        this.operations = compte.getOperations().stream()
                .filter(op -> op.getDate().isAfter(unMoisAvant))
                .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate())) // antéchronologique
                .collect(Collectors.toList());
    }

    public TypeCompteEnum getTypeCompte() {
        return typeCompte;
    }

    public double getSoldeActuel() {
        return soldeActuel;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Relevé de Compte ===\n");
        sb.append("Type de compte : ").append(typeCompte).append("\n");
        sb.append("Solde actuel   : ").append(soldeActuel).append(" €\n");
        sb.append("Opérations du dernier mois :\n");
        if (operations.isEmpty()) {
            sb.append("  (Aucune opération ce mois-ci)\n");
        } else {
            for (Operation op : operations) {
                sb.append("  - ")
                        .append(op.getDate().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .append(" | ")
                        .append(op.getType())
                        .append(" | montant: ")
                        .append(op.getMontant()).append(" €")
                        .append(" | solde après opération: ")
                        .append(op.getSoldeApresOperation()).append(" €")
                        .append("\n");
            }
        }
        return sb.toString();
    }
}
