package com.example.reciclagem.controllers;

import com.example.reciclagem.dtos.AlertaResponse;
import com.example.reciclagem.services.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @GetMapping
    public ResponseEntity<List<AlertaResponse>> listarAtivos() {
        return ResponseEntity.ok(alertaService.listarAtivos());
    }
}
