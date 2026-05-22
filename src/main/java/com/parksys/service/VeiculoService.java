package com.parksys.service;

import com.parksys.dto.VeiculoRequest;
import com.parksys.dto.VeiculoResponse;
import com.parksys.exception.BusinessException;
import com.parksys.model.Caminhonete;
import com.parksys.model.Carro;
import com.parksys.model.Moto;
import com.parksys.model.Veiculo;
import com.parksys.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoResponse cadastrar(VeiculoRequest request) {
        if (veiculoRepository.existsByPlaca(request.placa())) {
            throw new BusinessException("Placa já cadastrada: " + request.placa());

        }

        Veiculo veiculo = criarVeiculoPorTipo(request);
        veiculo.setPlaca(request.placa());
        veiculo.setModelo(request.modelo());
        veiculo.setCor(request.cor());

        Veiculo salvo = veiculoRepository.save(veiculo);
        return toResponse(salvo);
    }

    public List<VeiculoResponse> listarTodos(){
        return veiculoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

    }

    public void remover (Long id){
        if (!veiculoRepository.existsById(id)){
            throw new BusinessException("Veiculo não encontrado com ID: " + id);
        }
        veiculoRepository.deleteById(id);

    }

    // Método privado — decide qual subclasse criar (polimorfismo)
    private Veiculo criarVeiculoPorTipo(VeiculoRequest request) {
        return switch (request.tipo().toUpperCase()) {
            case "CARRO"       -> new Carro();
            case "MOTO"        -> new Moto();
            case "CAMINHONETE" -> new Caminhonete();
            default -> throw new BusinessException("Tipo inválido: " + request.tipo());
        };
    }

    // Método privado — converte Entity para DTO
    private VeiculoResponse toResponse(Veiculo v) {
        return new VeiculoResponse(
            v.getId(),
            v.getPlaca(),
            v.getModelo(),
            v.getCor(),
            v.getClass().getSimpleName().toUpperCase()
        );
    }
}
