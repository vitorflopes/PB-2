package com.example.consulta_service.service.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PrescricaoClient {

    private final RestTemplate restTemplate;

    public boolean verificarPrescricao(Integer consultaId) {
        // Substitua pela URL real do seu serviço de prescrições
        String url = "http://localhost:8086/prescricoes/consulta/" + consultaId;
        try {
            // Obtém um boolean indicando a presença da prescrição
            return restTemplate.getForObject(url, Boolean.class);
        } catch (Exception e) {
            // Trata erros na chamada ao serviço de prescrições
            return false;
        }
    }
}