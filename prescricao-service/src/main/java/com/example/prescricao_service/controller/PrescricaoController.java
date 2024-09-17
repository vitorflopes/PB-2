package com.example.prescricao_service.controller;

import com.example.prescricao_service.model.Prescricao;
import com.example.prescricao_service.service.PrescricaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Cria uma nova prescrição", description = "Cria uma nova prescrição no sistema com os parâmetros fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Prescrição criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping("/")
    public ResponseEntity<Prescricao> criarPrescricao(@RequestBody Prescricao prescricao) {
        Prescricao novaPrescricao = prescricaoService.criarPrescricao(prescricao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPrescricao);
    }

    @Operation(summary = "Verifica a existência de uma prescrição", description = "Verifica se há uma prescrição associada a uma consulta com base no ID da consulta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prescrição verificada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Prescrição não encontrada")
    })
    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<Boolean> verificarPrescricao(@PathVariable Integer consultaId) {
        boolean existePrescricao = prescricaoService.verificarPrescricao(consultaId).isPresent();
        return ResponseEntity.ok(existePrescricao);
    }

    @Operation(summary = "Obtém detalhes da prescrição por ID da consulta", description = "Obtém os detalhes de uma prescrição associada a uma consulta com base no ID da consulta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalhes da prescrição obtidos com sucesso"),
            @ApiResponse(responseCode = "404", description = "Prescrição não encontrada")
    })
    @GetMapping("/consulta/{consultaId}/detalhes")
    public ResponseEntity<?> obterPrescricaoPorConsultaId(@PathVariable Integer consultaId) {
        Prescricao prescricao = prescricaoService.obterPrescricaoPorConsultaId(consultaId);
        if (prescricao != null) {
            return ResponseEntity.ok(prescricao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Prescrição não encontrada para o ID de consulta fornecido.");
        }
    }
}