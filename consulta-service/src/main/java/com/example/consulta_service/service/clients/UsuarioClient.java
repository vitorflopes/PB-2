package com.example.consulta_service.service.clients;

import com.example.consulta_service.DTO.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuario-service")
public interface UsuarioClient {

    @GetMapping("/{id}")
    UsuarioDTO getUsuarioById(@PathVariable("id") Integer id);
}
