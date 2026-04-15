package com.example.reciclagem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertaResponse {

    private Long id;
    private String mensagem;
    private String tipo;
    private Boolean ativo;

}
