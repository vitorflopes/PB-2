package com.example.consulta_service.service;

import com.example.consulta_service.model.Consulta;
import com.example.consulta_service.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultaService {
    private final ConsultaRepository consultaRepository;

    public Consulta create(Consulta consulta) {
        return consultaRepository.save(consulta);
    }
    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }
    public Optional<Consulta> findById(Long id) {
        return consultaRepository.findById(id);
    }
    public void deleteById(Long id) {
        consultaRepository.deleteById(id);
    }
    public Consulta update(Consulta consulta) {
        return consultaRepository.save(consulta);
    }
}
