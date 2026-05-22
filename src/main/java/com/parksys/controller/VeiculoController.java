package com.parksys.controller;

import com.parksys.dto.VeiculoRequest;
import com.parksys.dto.VeiculoResponse;
import com.parksys.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService service;

    @PostMapping
    public ResponseEntity<VeiculoResponse> cadastrar (
            @Valid @RequestBody VeiculoRequest request ){
                return ResponseEntity.status(201).body(service.cadastrar(request));

    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponse>> listar () {
        return ResponseEntity.ok(service.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

}
