package com.example.consulta_service.service.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailClient {

    private final RestTemplate restTemplate;

    public void enviarEmail(String recipient, String subject, String body) {
        String url = "http://JAVAMAIL:8085/emails/send";

        Map<String, String> emailData = new HashMap<>();
        emailData.put("recipient", recipient);
        emailData.put("subject", subject);
        emailData.put("body", body);

        try {
            restTemplate.postForObject(url, emailData, String.class);
        } catch (Exception e) {
            throw new IllegalStateException("Não foi possível enviar o email: " + e.getMessage() + recipient);
        }
    }
}

