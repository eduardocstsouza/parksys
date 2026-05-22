package com.parksys.service;

import com.parksys.dto.SaidaResponse;
import com.parksys.exception.BusinessException;
import com.parksys.model.Movimentacao;
import com.parksys.model.Vaga;
import com.parksys.model.Veiculo;
import com.parksys.repository.MovimentacaoRepository;
import com.parksys.repository.VagaRepository;
import com.parksys.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {

    private final VeiculoRepository veiculoRepository;
    private final VagaRepository vagaRepository;
    private final MovimentacaoRepository movimentacaoRepository;
    private final CobrancaService cobrancaService;

    @Transactional
    public Movimentacao registrarEntrada(String placa, Integer numeroVaga) {

        Veiculo veiculo = veiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new BusinessException("Veiculo nao encontrado: " + placa));

        boolean jaEstacionado = movimentacaoRepository
                .findByVeiculoAndStatus(veiculo, "ATIVO")
                .isPresent();
        if (jaEstacionado){
            throw new BusinessException("Veiculo ja esta no estacionamento");
        }
        Vaga vaga = vagaRepository.findByNumero(numeroVaga)
                .orElseThrow(() -> new BusinessException("Vaga nao encontrada: " + numeroVaga));

        if (vaga.isOcupada()) {
            throw new BusinessException("Vaga " + numeroVaga + " ja esta ocupada");
        }
        vaga.setOcupada(true);
        vagaRepository.save(vaga);

        Movimentacao mov = new Movimentacao();
        mov.setVeiculo(veiculo);
        mov.setVaga(vaga);
        mov.setDataEntrada(LocalDateTime.now());
        mov.setStatus("ATIVO");

        return movimentacaoRepository.save(mov);
    }

    @Transactional
    public SaidaResponse registrarSaida(Long movimentacaoId) {

        Movimentacao mov = movimentacaoRepository.findById(movimentacaoId)
                .orElseThrow(() -> new BusinessException("Movimentação não encontrada"));

        if (!"ATIVO".equals(mov.getStatus())) {
            throw new BusinessException("Esta movimentação já foi finalizada");
        }

        if (mov.getDataEntrada() == null) {
            throw new BusinessException("Registro de saída sem data de entrada");
        }

        LocalDateTime saida = LocalDateTime.now();
        mov.setDataSaida(saida);

        double valor = cobrancaService.calcular(
                mov.getVeiculo(),
                mov.getDataEntrada(),
                saida
        );
        mov.setValorPago(BigDecimal.valueOf(valor));
        mov.setStatus("FINALIZADO");

        mov.getVaga().setOcupada(false);
        vagaRepository.save(mov.getVaga());

        Movimentacao salva = movimentacaoRepository.save(mov);

        return new SaidaResponse(
                salva.getId(),
                salva.getVeiculo().getPlaca(),
                salva.getVaga().getNumero(),
                salva.getDataEntrada(),
                salva.getDataSaida(),
                salva.getValorPago()
        );
    }

    public List<Movimentacao> listarEstacionadosAgora() {
        return movimentacaoRepository.findByStatus("ATIVO");
    }

    public List<Movimentacao> listarHistorico() {
        return movimentacaoRepository.findAll();
    }

}
