package com.parksys.repository;

import com.parksys.model.Movimentacao;
import com.parksys.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    Optional<Movimentacao> findByVeiculoAndStatus (Veiculo veiculo, String status);

    List<Movimentacao> findByStatus (String status);
}
