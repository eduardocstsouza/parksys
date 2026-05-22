package com.parksys.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SaidaResponse(
        Long movimentacaoId,
        String placa,
        Integer vaga,
        LocalDateTime entrada,
        LocalDateTime saida,
        BigDecimal valorPago
) {
}
