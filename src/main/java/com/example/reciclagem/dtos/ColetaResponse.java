package com.example.reciclagem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColetaResponse {

    private Long id;
    private LocalDateTime dataColeta;
    private String localColeta;
    private String responsavel;
    private String tipoResiduo;
    private BigDecimal quantidadeKg;
}
