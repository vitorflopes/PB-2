package com.example.consulta_service.service;

import com.example.consulta_service.exception.ResourceNotFoundException;
import com.example.consulta_service.model.Consulta;
import com.example.consulta_service.repository.ConsultaRepository;
import com.example.consulta_service.service.clients.ConsultaClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultaService {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;
    private final ConsultaRepository consultaRepository;

    public Consulta create(Consulta consulta) throws JsonProcessingException {
        consulta = consultaRepository.save(consulta);

        amqpTemplate.convertAndSend(
                "consulta-exc",
                "rk-consulta",
                objectMapper.writeValueAsString(consulta)
        );

        return consulta;
    }

    public void mark(Consulta consulta) {
        consultaRepository.save(consulta);
    }

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }
    public Optional<Consulta> findById(Integer id) {
        if (!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta Não Localizada");
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
