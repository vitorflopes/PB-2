package com.example.consulta_service.controller;

import com.example.consulta_service.exception.ResourceNotFoundException;
import com.example.consulta_service.model.Consulta;
import com.example.consulta_service.payload.MessagePayload;
import com.example.consulta_service.service.ConsultaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3001", "http://localhost:3000"})
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class ConsultaController {
    private final ConsultaService consultaService;

    @Operation(summary = "Cadastra uma nova consulta", description = "Cadastra uma nova consulta no sistema com os parâmetros passados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta cadastrada")
    })
    @PostMapping("/")
    public ResponseEntity<Consulta> createConsulta(@RequestBody Consulta consulta) {
        Consulta saved = null;
        try {
            saved = consultaService.create(consulta);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "Retornar Consulta", description = "Retorna as consultas do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    @GetMapping("/")
    public ResponseEntity<?> getConsultas() {
        return ResponseEntity.ok(consultaService.findAll());
    }

    @Operation(summary = "Retorna Consulta por ID", description = "Retorna uma consulta específica do sistema por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Esse ID não foi encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getConsulta(@PathVariable Integer id) {
        Optional<Consulta> optional = consultaService.findById(id);
        log.error(String.valueOf(optional));
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deletar Consulta", description = "Deleta uma consulta específica do sistema por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta deletada"),
            @ApiResponse(responseCode = "400", description = "Esse ID não foi encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable Integer id) {
        try {
            consultaService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Deletado com sucesso"));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(e.getMessage()));
        }
    }

    @Operation(summary = "Atualizar Consulta", description = "Atualiza uma consulta específica do sistema por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta atualizada"),
            @ApiResponse(responseCode = "400", description = "Esse ID não foi encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> update(@PathVariable Integer id, @RequestBody Consulta consulta) {
        try {
            consultaService.update(id, consulta);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @Operation(summary = "Finalizar Consulta", description = "Finaliza uma consulta específica do sistema por ID após verificar a prescrição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta finalizada"),
            @ApiResponse(responseCode = "400", description = "Não foi possível finalizar a consulta")
    })
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<MessagePayload> finalizarConsulta(@PathVariable Integer id) {
        try {
            consultaService.finalizarConsulta(id);
            return ResponseEntity.status(HttpStatus.OK).body(new MessagePayload("Consulta finalizada com sucesso"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(e.getMessage()));
        }
    }



}
