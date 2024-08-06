package com.example.consulta_service;

import com.example.consulta_service.model.Consulta;
import com.example.consulta_service.service.ConsultaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class ConsultaServiceApplicationTests {

	@Autowired
	private ConsultaService consultaService;

	@Test
	@DisplayName("Deve inserir uma consulta no banco.")
	public void testarInsert() {
		List<Consulta> all = consultaService.findAll();
		int estadoInicial = all.size();

		Consulta consulta = new Consulta();
		consulta.setData(LocalDate.of(2024, 6, 17));
		consulta.setPaciente(null);
		consulta.setConsultaFinalizada(true);
		consulta.setDiagnostico("Gripe");
		consultaService.create(consulta);

		all = consultaService.findAll();
		int estadoFinal = all.size();

		assertEquals(estadoInicial + 1, estadoFinal);
	}

	@Test
	@DisplayName("Deve buscar uma consulta por ID.")
	public void testarGetById() {
		Consulta consulta = new Consulta();
		consulta.setData(LocalDate.of(2024, 6, 18));
		consulta.setPaciente(null);
		consulta.setConsultaFinalizada(true);
		consulta.setDiagnostico("Resfriado");
		Consulta savedConsulta = consultaService.create(consulta);

		Optional<Consulta> consultaCadastrada = consultaService.findById(savedConsulta.getId());

		assertTrue(consultaCadastrada.isPresent());
		assertEquals(savedConsulta.getId(), consultaCadastrada.get().getId());
		assertEquals(savedConsulta.getDiagnostico(), consultaCadastrada.get().getDiagnostico());
	}
}
