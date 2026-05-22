package com.parksys.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EntradaRequest(
        @NotBlank(message = "Placa é obrigatória")
        String placa,

        @NotNull(message = "Número da vaga é obrigatório")
        Integer numeroVaga
) {
}
