package com.credits.repository;

import com.credits.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByNumeroNfse(String numeroNfse);
    Optional<Credit> findByNumeroCredito(String numeroCredito);
}

