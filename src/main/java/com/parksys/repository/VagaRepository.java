package com.parksys.repository;

import com.parksys.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

    List <Vaga> findByOcupadaFalse();

    Optional<Vaga> findByNumero(Integer numero);
}
