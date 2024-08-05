package com.example.consulta_service.controller;

import com.example.consulta_service.model.Consulta;
import com.example.consulta_service.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ConsultaController {
    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<Consulta> createConsulta(@RequestBody Consulta consulta) {
        Consulta saved = consultaService.create(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<?> getConsultas() {
        return ResponseEntity.ok(consultaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getConsulta(@PathVariable Long id) {
        Optional<Consulta> optional = consultaService.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        consultaService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Consulta consulta) {
        consultaService.update(consulta);
        return ResponseEntity.ok().build();
    }
}
