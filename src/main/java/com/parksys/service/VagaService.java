package com.parksys.service;

import com.parksys.model.Vaga;
import com.parksys.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VagaService {

    private final VagaRepository vagaRepository;

    public List<Vaga> listarTodas(){
        return vagaRepository.findAll();
    }

    public List<Vaga> listarLivres(){
        return vagaRepository.findByOcupadaFalse();
    }
}
