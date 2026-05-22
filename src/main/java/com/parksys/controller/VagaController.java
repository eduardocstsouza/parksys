package com.parksys.controller;

import com.parksys.model.Vaga;
import com.parksys.service.VagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vagas")
@RequiredArgsConstructor

public class VagaController {

    private final VagaService service;

    @GetMapping
    public ResponseEntity<List<Vaga>> listarTodas(){
        return ResponseEntity.ok(service.listarTodas());

    }

    @GetMapping("/livres")
    public ResponseEntity<List<Vaga>> listarLivres(){
        return ResponseEntity.ok(service.listarLivres());
    }
}
