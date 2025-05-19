package com.credits;

import com.credits.dto.CreditDto;
import com.credits.mappers.CreditMapper;
import com.credits.model.Credit;
import com.credits.repository.CreditRepository;
import com.credits.service.CreditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditServiceTest {

    @Mock private CreditRepository repo;
    @Mock private KafkaTemplate<String, String> kafka;
    @Mock private CreditMapper creditMapper;
    @InjectMocks private CreditService service;

    private Credit sample;
    private CreditDto sampleDto;

    @BeforeEach
    void setupEntity() {

        sample = new Credit();
        sample.setNumeroCredito("123");
        sample.setNumeroNfse("NF-001");
        sample.setDataConstituicao(LocalDate.of(2024,2,25));
        sample.setValorIssqn(new BigDecimal("1500.75"));

        sampleDto = new CreditDto();
    }

    @Test
    void buscarPorNfse_devolveListaEpublicaEvento() {
        when(repo.findByNumeroNfse("NF-001")).thenReturn(List.of(sample));
        when(creditMapper.toDto(sample)).thenReturn(sampleDto);

        var dtos = service.buscarPorNfse("NF-001");

        assertThat(dtos).containsExactly(sampleDto);
        verify(kafka).send(eq("consulta-creditos"), contains("Consulta NFS-e: NF-001"));
    }

    @Test
    void buscarPorNumero_devolveDtoEpublicaEvento() {
        when(repo.findByNumeroCredito("123")).thenReturn(Optional.of(sample));
        when(creditMapper.toDto(sample)).thenReturn(sampleDto);

        var dto = service.buscarPorNumero("123");

        assertThat(dto).isSameAs(sampleDto);
        verify(kafka).send(eq("consulta-creditos"), contains("Consulta Crédito: 123"));
    }

    @Test
    void buscarPorNumero_lanca404_quandoNaoEncontrado() {
        when(repo.findByNumeroCredito("999")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorNumero("999"))
                .isInstanceOf(ResponseStatusException.class);

        verify(kafka, never()).send(any(), anyString());
    }
}


