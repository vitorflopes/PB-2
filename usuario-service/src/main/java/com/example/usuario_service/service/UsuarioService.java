package com.example.usuario_service.service;

import com.example.usuario_service.exception.ResourceNotFoundException;
import com.example.usuario_service.model.Usuario;
import com.example.usuario_service.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
    public Optional<Usuario> findById(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário Não Localizado");
        }
        return usuarioRepository.findById(id);
    }
    public void deleteById(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário Não Localizado");
        }
        usuarioRepository.deleteById(id);
    }

    public void update(Integer id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário Não Localizado");
        }
        usuario.setId(id);
        usuarioRepository.save(usuario);
    }
}
