package com.example.reciclagem.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResiduoRequest {
    @NotBlank(message = "O tipo do resíduo é obrigatório.")
    @Size(max = 60, message = "O tipo deve ter no máximo 60 caracteres.")
    private String tipo;

    @Size(max = 400, message = "A descrição deve ter no máximo 400 caracteres.")
    private String descricao;

    @DecimalMin(value = "0.0", inclusive = false, message = "O peso médio deve ser maior que zero.")
    private BigDecimal pesoMedioKg;

}
