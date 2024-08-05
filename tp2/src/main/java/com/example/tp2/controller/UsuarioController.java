package com.example.tp2.controller;

import com.example.tp2.exception.ResourceNotFoundException;
import com.example.tp2.model.Consulta;
import com.example.tp2.model.Usuario;
import com.example.tp2.payload.MessagePayload;
import com.example.tp2.service.ConsultaService;
import com.example.tp2.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAll(){
        return usuarioService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try{
            Usuario localizada = usuarioService.getById(id);
            return ResponseEntity.ok(localizada);
        }
        catch (ResourceNotFoundException ex){
            Map<String, String> message = Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PostMapping
    public ResponseEntity<MessagePayload> save(@RequestBody Usuario usuario){
        usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> update(@PathVariable Integer id, @RequestBody Usuario usuario){
        try {
            usuarioService.update(id,usuario);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable Integer id){
        //logger.info("Deletando uma cerveja");
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Deletado com sucesso"));
        }catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
