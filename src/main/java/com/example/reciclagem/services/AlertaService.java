package com.example.reciclagem.services;

import com.example.reciclagem.dtos.AlertaResponse;
import com.example.reciclagem.models.AlertaModel;
import com.example.reciclagem.models.ResiduoModel;
import com.example.reciclagem.repositories.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Value("${app.alertas.limiteKg}")
    private BigDecimal limiteKgPorColeta;


    @Transactional(readOnly = true)
    public List<AlertaResponse> listarAtivos(){
        return alertaRepository.findByAtivoTrue().stream()
                .map(a  -> new AlertaResponse(a.getId(), a.getMensagem(), a.getTipo(), a.getAtivo()))
                .toList();
    }

    @Transactional
    public void criarAlertaAutomaticoSeNecessario(ResiduoModel residuoModel, BigDecimal pesoMedioKg){
        if (pesoMedioKg == null || residuoModel == null) return;

        if(pesoMedioKg.compareTo(BigDecimal.ZERO) <= 0){
            String mensagem = String.format(
                    "Coleta excedeu o limite (%.3f kg) para o resíduo '%s'. Quantidade registrada: %.3f kg.",
                    limiteKgPorColeta, residuoModel.getTipo(), pesoMedioKg);

            AlertaModel alertaModel = new AlertaModel(mensagem, "LIMITE_COLETA_EXCEDIDO", true);
            alertaRepository.save(alertaModel);
        }
    }

    @Transactional
    public AlertaModel criarAlertaManual(String mensagem, String tipo, Boolean ativo) {
        AlertaModel alerta = new AlertaModel(mensagem, tipo, ativo != null ? ativo : Boolean.TRUE);
        return alertaRepository.save(alerta);
    }

}
