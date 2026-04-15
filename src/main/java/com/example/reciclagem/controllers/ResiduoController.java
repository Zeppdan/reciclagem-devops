package com.example.reciclagem.controllers;

import com.example.reciclagem.dtos.ResiduoRequest;
import com.example.reciclagem.dtos.ResiduoResponse;
import com.example.reciclagem.services.ResiduoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residuos")
public class ResiduoController {

    @Autowired
    private ResiduoService residuoService;

    @PostMapping
    public ResponseEntity<ResiduoResponse> cadastrar(@RequestBody @Valid ResiduoRequest request) {
        ResiduoResponse novo = residuoService.cadastrar(request);
        return ResponseEntity.ok(novo);
    }

    @GetMapping
    public ResponseEntity<List<ResiduoResponse>> listar() {
        return ResponseEntity.ok(residuoService.listar());
    }

}
