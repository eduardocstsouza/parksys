package com.parksys.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("MOTO")
@Getter @Setter @NoArgsConstructor

public class Moto extends Veiculo {
    @Override
    public double calcularValor(double valorBase) {
        return valorBase * 0.5;
    }
}
