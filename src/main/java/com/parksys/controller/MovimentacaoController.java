package com.parksys.controller;

import com.parksys.dto.EntradaRequest;
import com.parksys.dto.SaidaResponse;
import com.parksys.model.Movimentacao;
import com.parksys.service.MovimentacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movimentacoes")
@RequiredArgsConstructor

public class MovimentacaoController {

    private final MovimentacaoService service;

    @PostMapping("/entrada")
    public ResponseEntity<Movimentacao> registrarEntrada(
            @Valid @RequestBody EntradaRequest request) {
        Movimentacao mov = service.registrarEntrada(request.placa(), request.numeroVaga());
        return ResponseEntity.status(201).body(mov);
    }

    @PutMapping("/{id}/saida")
    public ResponseEntity<SaidaResponse> registrarSaida(@PathVariable Long id) {
        return ResponseEntity.ok(service.registrarSaida(id));
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<Movimentacao>> listarEstacionados() {
        return ResponseEntity.ok(service.listarEstacionadosAgora());
    }

    @GetMapping
    public ResponseEntity<List<Movimentacao>> listarHistorico() {
        return ResponseEntity.ok(service.listarHistorico());
    }
}

