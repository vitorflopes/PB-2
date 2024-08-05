package com.example.tp2.service;

import com.example.tp2.exception.ResourceNotFoundException;
import com.example.tp2.model.Consulta;
import com.example.tp2.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> getAll() {
        return consultaRepository.findAll();
    }

    public Consulta getById(Integer id) {
        if (id < 0) {
            throw new ResourceNotFoundException("Valor Invalido - NAO PODE SER NEGATIVO");
        } else {
            Optional<Consulta> consultaOpt = consultaRepository.findById(id);
            if (consultaOpt.isEmpty()) throw new ResourceNotFoundException("Objeto Nao Encontrado");
            return consultaOpt.get();
        }
    }

    public void save(Consulta consulta) {
        consultaRepository.save(consulta);
    }

    public void update(Integer id, Consulta consulta) {
        if (!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta Não Localizada");
        }
        consulta.setId(id); // Ensure the ID remains the same
        consultaRepository.save(consulta);
    }

    public void deleteById(Integer id) {
        if (!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta não localizada");
        }
        consultaRepository.deleteById(id);
    }
}
