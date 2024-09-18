package com.example.usuario_service;

import com.example.usuario_service.model.Usuario;
import com.example.usuario_service.repository.UsuarioRepository;
import com.example.usuario_service.service.UsuarioService;
import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class UsuarioServiceApplicationTests {

	/*
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
		usuario.setSenha("123456");
		usuario.setDoctor(false);
		usuario.setEspecialidade("paciente");
		usuario.setConsultas(List.of(1, 2));
		usuario.setDatasIndisponiveis(List.of(
				LocalDate.of(2024, 9, 16),
				LocalDate.of(2024, 9, 17)
		));
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

	 */
}
