package com.example.bank.repository;

import com.example.bank.model.LivretEpargne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivretEpargneRepository extends JpaRepository<LivretEpargne, Long> {
}
