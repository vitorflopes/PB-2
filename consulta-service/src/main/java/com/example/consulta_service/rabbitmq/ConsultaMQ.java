package com.example.consulta_service.rabbitmq;

import com.example.consulta_service.model.Consulta;
import com.example.consulta_service.service.ConsultaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsultaMQ {
    private final ObjectMapper objectMapper;
    private final ConsultaService consultaService;

    @RabbitListener(queues = {"fila-consulta-processada"})
    public void receive(@Payload String json) {
        System.out.println("ConsultaService pegou a msg da fila fila-consulta-processada: " + json);
        try {
            Consulta consulta = objectMapper.readValue(json, Consulta.class);
            consultaService.mark(consulta);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
