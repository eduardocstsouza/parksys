package com.parksys.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table (name = "movimentacoes")
@Getter @Setter @NoArgsConstructor

public class Movimentacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vaga_id", nullable = false)
    private Vaga vaga;

    @Column (name = "data_entrada", nullable = false)
    private LocalDateTime dataEntrada;

    @Column (name = "data_saida")
    private LocalDateTime dataSaida;

    @Column (name = "valor_pago")
    private BigDecimal valorPago;

    private String status = "ATIVO";

}
