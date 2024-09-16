package com.example.usuario_service.controller;

import com.example.usuario_service.dto.AuthRequest;
import com.example.usuario_service.dto.AuthResponse;
import com.example.usuario_service.exception.ResourceNotFoundException;
import com.example.usuario_service.model.Usuario;
import com.example.usuario_service.payload.MessagePayload;
import com.example.usuario_service.security.JwtService;
import com.example.usuario_service.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    @Operation(summary = "Cria um novo usuário", description = "Cadastra um novo usuário no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class),
                            examples = @ExampleObject(value = "{\"id\": 1, \"nome\": \"João Silva\", \"email\": \"joao@example.com\", \"senha\": \"senha123\", \"isDoctor\": true, \"especialidade\": \"Cardiologia\"}")
                    )),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario saved = usuarioService.create(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "Autentica um usuário", description = "Realiza a autenticação de um usuário com email e senha, retornando um token JWT se bem-sucedido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticação bem-sucedida, JWT retornado"),
            @ApiResponse(responseCode = "401", description = "Falha na autenticação, login ou senha incorretos")
    })
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
        Optional<Usuario> usuarioOptional = usuarioService.findByEmail(authRequest.getLogin());

        if (usuarioOptional.isPresent() && usuarioOptional.get().getSenha().equals(authRequest.getSenha())) {
            String token = jwtService.generateToken(usuarioOptional.get().getEmail());
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senha incorretos");
        }
    }

    @Operation(summary = "Obtém todos os usuários", description = "Recupera a lista de todos os usuários cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    })
    @GetMapping("/")
    public ResponseEntity<?> getUsuarios() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @Operation(summary = "Obtém um usuário por ID", description = "Recupera os detalhes de um usuário específico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Integer id) {
        Optional<Usuario> optional = usuarioService.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deleta um usuário por ID", description = "Remove um usuário do sistema pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable Integer id) {
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Deletado com sucesso"));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(e.getMessage()));
        }
    }

    @Operation(summary = "Atualiza um usuário por ID", description = "Atualiza os dados de um usuário específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> update(@PathVariable Integer id, @RequestBody Usuario usuario) {
        try {
            usuarioService.update(id, usuario);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
