package com.credits.resource;

import com.credits.dto.CreditDto;
import com.credits.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
@RequiredArgsConstructor
public class CreditResource {

    private final CreditService creditService;

    @GetMapping("/{numeroNfse}")
    public List<CreditDto> getByNfse(@PathVariable String numeroNfse) {
        return creditService.buscarPorNfse(numeroNfse);
    }

    @GetMapping("/credito/{numeroCredito}")
    public CreditDto getByCredito(@PathVariable String numeroCredito) {
        return creditService.buscarPorNumero(numeroCredito);
    }
}

