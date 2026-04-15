package com.example.reciclagem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_COLETA")
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "seq_coleta", sequenceName = "SQ_COLETA", allocationSize = 1)
public class ColetaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_coleta")
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA_COLETA", nullable = false)
    private LocalDateTime dataColeta;

    @Column(name = "LOCAL_COLETA", nullable = false, length = 200)
    private String localColeta;

    @Column(name = "RESPONSAVEL", nullable = false, length = 120)
    private String responsavel;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "RESIDUO_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_COLETA_RESIDUO"))
    private ResiduoModel residuo;

    @Column(name = "QUANTIDADE_KG", nullable = false, precision = 12, scale = 3)
    private BigDecimal quantidadeKg;

    public ColetaModel(LocalDateTime dataColeta, String localColeta, String responsavel,
                  ResiduoModel residuo, BigDecimal quantidadeKg) {
        this.dataColeta = dataColeta;
        this.localColeta = localColeta;
        this.responsavel = responsavel;
        this.residuo = residuo;
        this.quantidadeKg = quantidadeKg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDateTime dataColeta) {
        this.dataColeta = dataColeta;
    }

    public String getLocalColeta() {
        return localColeta;
    }

    public void setLocalColeta(String localColeta) {
        this.localColeta = localColeta;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public ResiduoModel getResiduo() {
        return residuo;
    }

    public void setResiduo(ResiduoModel residuo) {
        this.residuo = residuo;
    }

    public BigDecimal getQuantidadeKg() {
        return quantidadeKg;
    }

    public void setQuantidadeKg(BigDecimal quantidadeKg) {
        this.quantidadeKg = quantidadeKg;
    }
}
