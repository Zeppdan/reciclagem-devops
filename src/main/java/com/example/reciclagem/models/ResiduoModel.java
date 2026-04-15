package com.example.reciclagem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_RESIDUO")
@SequenceGenerator(name = "seq_residuo", sequenceName = "SQ_RESIDUO", allocationSize = 1)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResiduoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_residuo")
    private Long id;

    @Column(name = "TIPO", nullable = false, length = 60)
    private String tipo;

    @Column(name = "DESCRICAO", length = 400)
    private String descricao;

    @Column(name = "PESO_MEDIO_KG", precision = 10, scale = 3)
    private BigDecimal pesoMedioKg;

    public ResiduoModel(String tipo, String descricao, BigDecimal pesoMedioKg) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.pesoMedioKg = pesoMedioKg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPesoMedioKg() {
        return pesoMedioKg;
    }

    public void setPesoMedioKg(BigDecimal pesoMedioKg) {
        this.pesoMedioKg = pesoMedioKg;
    }
}
