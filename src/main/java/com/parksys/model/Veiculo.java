package com.parksys.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "veiculos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@Getter @Setter @NoArgsConstructor

public abstract class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true, nullable = false, length = 10)
    private String placa;

    private String modelo;
    private String cor;

    @Column (name = "criado_em")
    private LocalDateTime criadoEm = LocalDateTime.now();

    public abstract double calcularValor(double valorBase);

}
