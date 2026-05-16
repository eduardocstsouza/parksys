package com.parksys.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "vagas")
@Getter @Setter @NoArgsConstructor

public class Vaga {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer numero;

    @Column(nullable = false)
    private boolean ocupada = false;

    @Column(name = "criado_em", updatable = false, insertable = false)
    private LocalDateTime criadoEm;

}
