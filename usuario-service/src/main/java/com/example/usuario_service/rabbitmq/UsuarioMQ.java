package com.example.usuario_service.rabbitmq;

import com.example.usuario_service.model.Consulta;
import com.example.usuario_service.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
@Service
@Transactional
@Slf4j
public class UsuarioMQ {
    private final ObjectMapper objectMapper;
    private final AmqpTemplate amqpTemplate;
    private final UsuarioService usuarioService;
    @RabbitListener(queues = {"fila-consulta"})
    public void receive(@Payload String json) {
        System.out.println("UsusarioService pegou a msg da fila fila-consulta: " + json);
        try {
            Consulta consulta = objectMapper.readValue(json, Consulta.class);
            log.info("Consulta: {}", json);

            consulta.setConsultaMarcada(usuarioService.verifyDate(consulta));
            amqpTemplate.convertAndSend(
                    "consulta-processada-exc",
                    "rk-consulta-processada",
                    objectMapper.writeValueAsString(consulta)
            );
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
