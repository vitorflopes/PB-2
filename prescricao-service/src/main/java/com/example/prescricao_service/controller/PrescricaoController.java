package com.example.prescricao_service.controller;

import com.example.prescricao_service.model.Prescricao;
import com.example.prescricao_service.service.PrescricaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prescricoes")
@RequiredArgsConstructor
public class PrescricaoController {

    private final PrescricaoService prescricaoService;

    @PostMapping("/")
    public ResponseEntity<Prescricao> criarPrescricao(@RequestBody Prescricao prescricao) {
        Prescricao novaPrescricao = prescricaoService.criarPrescricao(prescricao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPrescricao);
    }

    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<Boolean> verificarPrescricao(@PathVariable Integer consultaId) {
        boolean existePrescricao = prescricaoService.verificarPrescricao(consultaId).isPresent();
        return ResponseEntity.ok(existePrescricao);
    }
}