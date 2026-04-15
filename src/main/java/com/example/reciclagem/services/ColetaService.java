package com.example.reciclagem.services;

import com.example.reciclagem.dtos.ColetaRequest;
import com.example.reciclagem.dtos.ColetaResponse;
import com.example.reciclagem.models.ColetaModel;
import com.example.reciclagem.models.ResiduoModel;
import com.example.reciclagem.repositories.ColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ColetaService {

    @Autowired
    private ColetaRepository coletaRepository;

    @Autowired
    private ResiduoService residuoService;

    @Autowired
    private AlertaService alertaService;

    public ColetaService(ColetaRepository coletaRepository,
                         ResiduoService residuoService,
                         AlertaService alertaService) {
        this.coletaRepository = coletaRepository;
        this.residuoService = residuoService;
        this.alertaService = alertaService;
    }

    @Transactional
    public ColetaResponse registrar(ColetaRequest request) {
        //Carrega o resíduo garantido (ou dispara 404)
        ResiduoModel residuo = residuoService.obterEntidadePorId(request.getResiduoId());

        ColetaModel entity = new ColetaModel(
                request.getDataColeta(),
                request.getLocalColeta(),
                request.getResponsavel(),
                residuo,
                request.getQuantidadeKg()
        );

        entity = coletaRepository.save(entity);

        //Avalia alerta automático
        alertaService.criarAlertaAutomaticoSeNecessario(residuo, request.getQuantidadeKg());

        return new ColetaResponse(
                entity.getId(),
                entity.getDataColeta(),
                entity.getLocalColeta(),
                entity.getResponsavel(),
                residuo.getTipo(),
                entity.getQuantidadeKg()
        );
    }

    @Transactional(readOnly = true)
    public List<ColetaResponse> listar() {
        return coletaRepository.findAll().stream()
                .map(coletaModel -> new ColetaResponse(
                        coletaModel.getId(),
                        coletaModel.getDataColeta(),
                        coletaModel.getLocalColeta(),
                        coletaModel.getResponsavel(),
                        coletaModel.getResiduo().getTipo(),
                        coletaModel.getQuantidadeKg()
                ))
                .toList();
    }


}
