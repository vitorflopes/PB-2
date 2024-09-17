package com.example.consulta_service.service.clients;

import com.example.consulta_service.DTO.PrescricaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PrescricaoClient {

    private final RestTemplate restTemplate;

    public boolean verificarPrescricao(Integer consultaId) {
        String url = "http://localhost:8086/prescricoes/consulta/" + consultaId;
        try {
            return restTemplate.getForObject(url, Boolean.class);
        } catch (Exception e) {
            return false;
        }
    }

    public PrescricaoDTO obterPrescricaoPorConsultaId(Integer consultaId) {
        String url = "http://localhost:8086/prescricoes/consulta/" + consultaId + "/detalhes";
        try {
            return restTemplate.getForObject(url, PrescricaoDTO.class);
        } catch (Exception e) {
            return null;
        }
    }

}