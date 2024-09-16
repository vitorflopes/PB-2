package com.example.prescricao_service.service;

import com.example.prescricao_service.model.Prescricao;
import com.example.prescricao_service.repository.PrescricaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrescricaoService {

    private final PrescricaoRepository prescricaoRepository;

    public Prescricao criarPrescricao(Prescricao prescricao) {
        return prescricaoRepository.save(prescricao);
    }

    public Optional<Prescricao> verificarPrescricao(Integer consultaId) {
        return prescricaoRepository.findByConsultaId(consultaId);
    }
}