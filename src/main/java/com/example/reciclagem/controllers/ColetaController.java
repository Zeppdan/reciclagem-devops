package com.example.reciclagem.controllers;

import com.example.reciclagem.dtos.ColetaRequest;
import com.example.reciclagem.dtos.ColetaResponse;
import com.example.reciclagem.services.ColetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coletas")
public class ColetaController {

    @Autowired
    private ColetaService coletaService;

    @PostMapping
    public ResponseEntity<ColetaResponse> registrar(@RequestBody @Valid ColetaRequest request) {
        ColetaResponse coleta = coletaService.registrar(request);
        return ResponseEntity.ok(coleta);
    }

    @GetMapping
    public ResponseEntity<List<ColetaResponse>> listar() {
        return ResponseEntity.ok(coletaService.listar());
    }
}
