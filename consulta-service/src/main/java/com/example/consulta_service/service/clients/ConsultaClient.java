package com.example.consulta_service.service.clients;

import com.example.consulta_service.model.Consulta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("CONSULTA-SERVICE")
public interface ConsultaClient {
    @GetMapping("/")
    List<Consulta> getConsultas();
}
