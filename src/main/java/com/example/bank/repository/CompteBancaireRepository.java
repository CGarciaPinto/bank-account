package com.example.bank.repository;

import com.example.bank.model.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, Long> {
}
