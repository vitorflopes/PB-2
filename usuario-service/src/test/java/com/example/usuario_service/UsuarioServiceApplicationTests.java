package com.example.usuario_service;

import com.example.usuario_service.model.Usuario;
import com.example.usuario_service.repository.UsuarioRepository;
import com.example.usuario_service.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class UsuarioServiceApplicationTests {

	@Autowired
	private UsuarioService usuarioService;

	@Test
	@DisplayName("Deve inserir um usuário no banco.")
	public void testarInsert() {
		List<Usuario> all = usuarioService.findAll();
		int estadoInicial = all.size();

		Usuario usuario = new Usuario();
		usuario.setNome("João");
		usuario.setEmail("joao@example.com");
		usuarioService.create(usuario);

		all = usuarioService.findAll();
		int estadoFinal = all.size();

		assertEquals(estadoInicial + 1, estadoFinal);
	}

	@Test
	@DisplayName("Deve buscar um usuário por ID.")
	public void testarGetById() {
		Usuario usuario = new Usuario();
		usuario.setNome("Maria");
		usuario.setEmail("maria@example.com");
		Usuario savedUsuario = usuarioService.create(usuario);

		Optional<Usuario> usuarioCadastrado = usuarioService.findById(savedUsuario.getId());

		assertTrue(usuarioCadastrado.isPresent());
		assertEquals(savedUsuario.getId(), usuarioCadastrado.get().getId());
		assertEquals(savedUsuario.getNome(), usuarioCadastrado.get().getNome());
	}
}
