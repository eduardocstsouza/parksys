package com.parksys.service;

import com.parksys.model.Veiculo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class CobrancaService {
    private static final double PRIMEIRA_HORA = 5.0;
    private static final double HORA_ADICIONAL = 3.0;

    public double calcular(Veiculo veiculo,
                           LocalDateTime entrada,
                           LocalDateTime saida){
    long minutos = ChronoUnit.MINUTES.between(entrada, saida);
    double horas = Math.ceil(minutos / 60.0);

    double base = horas <= 1
            ? PRIMEIRA_HORA
            : PRIMEIRA_HORA + (horas - 1 ) * HORA_ADICIONAL;

    return veiculo.calcularValor(base);

    }
}
