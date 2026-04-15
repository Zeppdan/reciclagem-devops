package com.example.reciclagem.services;

import com.example.reciclagem.dtos.ResiduoRequest;
import com.example.reciclagem.dtos.ResiduoResponse;
import com.example.reciclagem.exceptions.BusinessException;
import com.example.reciclagem.exceptions.ResourceNotFoundException;
import com.example.reciclagem.models.ResiduoModel;
import com.example.reciclagem.repositories.ResiduoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResiduoService {

    @Autowired
    private ResiduoRepository residuoRepository;

    @Transactional
    public ResiduoResponse cadastrar(ResiduoRequest request) {
        String tipo = request.getTipo().trim();

        if (residuoRepository.existsByTipoIgnoreCase(tipo)) {
            throw new BusinessException("Já existe um resíduo cadastrado com o tipo: " + tipo);
        }

        ResiduoModel entity = new ResiduoModel(tipo, request.getDescricao(), request.getPesoMedioKg());
        entity = residuoRepository.save(entity);

        return new ResiduoResponse(
                entity.getId(),
                entity.getTipo(),
                entity.getDescricao(),
                entity.getPesoMedioKg()
        );
    }

    @Transactional(readOnly = true)
    public List<ResiduoResponse> listar() {
        return residuoRepository.findAll().stream()
                .map(residuo -> new ResiduoResponse(residuo.getId(), residuo.getTipo(), residuo.getDescricao(), residuo.getPesoMedioKg()))
                .toList();
    }

    @Transactional(readOnly = true)
    public ResiduoModel obterEntidadePorId(Long id) {
        return residuoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resíduo não encontrado: id=" + id));
    }
}
