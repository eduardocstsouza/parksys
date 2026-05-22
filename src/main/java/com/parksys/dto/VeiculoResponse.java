package com.parksys.dto;

public record VeiculoResponse(
        Long id,
        String placa,
        String modelo,
        String cor,
        String tipo
) {
}
