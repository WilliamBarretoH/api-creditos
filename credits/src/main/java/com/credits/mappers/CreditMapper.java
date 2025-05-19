package com.credits.mappers;

import org.springframework.stereotype.Component;
import com.credits.dto.CreditDto;    // ou CreditoDto, conforme seu pacote
import com.credits.model.Credit;

@Component
public class CreditMapper implements CustomMapper<CreditDto, Credit> {

    @Override
    public CreditDto toDto(Credit entity) {

        return CreditDto.builder()
                .numeroCredito(entity.getNumeroCredito())
                .numeroNfse(entity.getNumeroNfse())
                .dataConstituicao(entity.getDataConstituicao())
                .valorIssqn(entity.getValorIssqn())
                .tipoCredito(entity.getTipoCredito())
                .simplesNacional(entity.isSimplesNacional() ? "Sim" : "Não")
                .aliquota(entity.getAliquota())
                .valorFaturado(entity.getValorFaturado())
                .valorDeducao(entity.getValorDeducao())
                .baseCalculo(entity.getBaseCalculo())
                .build();

    }
}
