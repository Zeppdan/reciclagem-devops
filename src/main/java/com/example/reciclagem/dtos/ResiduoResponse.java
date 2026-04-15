package com.example.reciclagem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResiduoResponse {

    private Long id;
    private String tipo;
    private String descricao;
    private BigDecimal pesoMedioKg;
}
