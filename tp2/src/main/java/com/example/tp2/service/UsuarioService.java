package com.example.tp2.service;

import com.example.tp2.exception.ResourceNotFoundException;
import com.example.tp2.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    List<Usuario> usuarios = initValues();

    private List<Usuario> initValues() {
        ArrayList<Usuario> usuarios1 = new ArrayList<>();
        usuarios1.add(new Usuario(1, "Igor", "igor@gmail.com", "123", false, "Clínico Drone", new ArrayList<>(), new ArrayList<>()));
        return usuarios1;
    }

    public List<Usuario> getAll() {
        return this.usuarios;
    }

    public Usuario getById(Integer id) {
        if (id < 0) {
            throw new ResourceNotFoundException("Valor Invalido - NAO PODE SER NEGATIVO");
        } else {
            Optional<Usuario> consultaOpt = usuarios.stream().filter(usuario -> usuario.getId().equals(id)).findFirst();
            if (consultaOpt.isEmpty()) throw new ResourceNotFoundException("Objeto Nao Encontrado");
            return consultaOpt.get();
        }
    }

    public void save(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void update(Integer id, Usuario usuario) {
        if (resourceNotFound(id)) {
            throw new ResourceNotFoundException("Usuário Não Localizado");
        }
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                usuarios.set(i, usuario);
                break;
            }
        }
    }

    public void deleteById(Integer id) {
        if (resourceNotFound(id)) {
            throw new ResourceNotFoundException("Usuário não localizado");
        }
        usuarios.removeIf(e -> e.getId().equals(id));
    }

    private boolean resourceNotFound(Integer id) {
        return usuarios.stream().filter(consulta -> consulta.getId().equals(id)).findFirst().isEmpty();
    }
}
