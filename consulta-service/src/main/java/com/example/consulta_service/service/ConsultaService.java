package com.example.consulta_service.service;

import com.example.consulta_service.exception.ResourceNotFoundException;
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
    public Optional<Consulta> findById(Integer id) {
        if (!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário Não Localizado");
        }
        return consultaRepository.findById(id);
    }
    public void deleteById(Integer id) {
        if (!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário Não Localizado");
        }
        consultaRepository.deleteById(id);
    }
    public void update(Integer id, Consulta consulta) {
        if(!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta Não Localizado");
        }
        consulta.setId(id);
        consultaRepository.save(consulta);
    }
}
