package com.example.usuario_service.service;

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
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
