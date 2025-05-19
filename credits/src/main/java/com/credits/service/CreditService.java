package com.credits.service;

import com.credits.dto.CreditDto;
import com.credits.mappers.CreditMapper;
import com.credits.repository.CreditRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository repo;
    private final KafkaTemplate<String, String> kafka;
    private final CreditMapper creditMapper;
    private final ObjectMapper objectMapper;

    public List<CreditDto> buscarPorNfse(String nfse) {
        List<CreditDto> dtos = repo.findByNumeroNfse(nfse)
                .stream().map(creditMapper::toDto).toList();
        kafka.send("consulta-creditos", "Consulta NFS-e: " + nfse);
        return dtos;
    }

    public CreditDto buscarPorNumero(String numero) {
        CreditDto dto = repo.findByNumeroCredito(numero)
                .map(creditMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        kafka.send("consulta-creditos", "Consulta Crédito: " + numero);
        return dto;
    }
}

