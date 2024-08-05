package com.example.tp2.controller;

import com.example.tp2.exception.ResourceNotFoundException;
import com.example.tp2.model.Consulta;
import com.example.tp2.payload.MessagePayload;
import com.example.tp2.service.ConsultaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consulta")
@Api(value = "Gerenciador consultas")
public class ConsultaController {
    final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornou com sucesso"),
            @ApiResponse(code = 401, message = "Sem permissão"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "Não encontrado")
    })
    @GetMapping
    public List<Consulta> getAll(){
        return consultaService.getAll();
    }

    @ApiOperation(value = "Retornar por id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try{
            Consulta localizada = consultaService.getById(id);
            return ResponseEntity.ok(localizada);
        }
        catch (ResourceNotFoundException ex){
            Map<String, String> message = Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @ApiOperation(value = "Criar a consulta")
    @PostMapping
    public ResponseEntity<MessagePayload> save(@RequestBody Consulta consulta){
        consultaService.save(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso"));
    }

    @ApiOperation(value = "Atualizar a consulta")
    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> update(@PathVariable Integer id, @RequestBody Consulta consulta){
        try {
            consultaService.update(id, consulta);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @ApiOperation(value = "Apagar a consulta")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable Integer id){
        try {
            consultaService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Deletado com sucesso"));
        }catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
