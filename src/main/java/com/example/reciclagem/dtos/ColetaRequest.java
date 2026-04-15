package com.example.reciclagem.dtos;

import jakarta.validation.constraints.*;
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
public class ColetaRequest {

    @NotNull(message = "A data da coleta é obrigatória.")
    @FutureOrPresent(message = "A data da coleta não pode ser no passado.")
    private LocalDateTime dataColeta;

    @NotBlank(message = "O local da coleta é obrigatório.")
    @Size(max = 200, message = "O local deve ter no máximo 200 caracteres.")
    private String localColeta;

    @NotBlank(message = "O nome do responsável é obrigatório.")
    @Size(max = 120, message = "O nome do responsável deve ter no máximo 120 caracteres.")
    private String responsavel;

    @NotNull(message = "O ID do resíduo é obrigatório.")
    private Long residuoId;

    @NotNull(message = "A quantidade é obrigatória.")
    @DecimalMin(value = "0.1", message = "A quantidade deve ser maior que zero.")
    private BigDecimal quantidadeKg;
}
