package com.parksys.dto;

import jakarta.validation.constraints.NotBlank;

public record VeiculoRequest(
        @NotBlank(message = "Placa é obrigatória")
        String placa,

        String modelo,
        String cor,

        @NotBlank(message = "Tipo é obrigatório")
        String tipo
) {

}
