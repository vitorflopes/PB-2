package com.example.tp2;

import com.example.tp2.model.Consulta;
import com.example.tp2.service.ConsultaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ConsultaServiceTests {
    @Autowired
    private ConsultaService consultaService;

    @Test
    @DisplayName("Deve inserir uma consulta no banco.")
    public void testarInsert() {
        List<Consulta> all = consultaService.getAll();
        int estadoInicial = all.size();

        Consulta consulta = new Consulta();
        consulta.setData(LocalDate.of(2024, 6, 17));
        consulta.setPaciente(null);
        consulta.setMedico(null);
        consulta.setConsultaFinalizada(true);
        consulta.setDiagnostico("Gripe");
        consultaService.save(consulta);

        all = consultaService.getAll();
        int estadoFinal = all.size();

        assertEquals(estadoInicial+1, estadoFinal);
    }

    @Test
    @DisplayName("Deve deletar uma consulta do banco.")
    public void testarDelete() {
        List<Consulta> all = consultaService.getAll();
        int estadoInicial = all.size();

        consultaService.deleteById(2);

        all = consultaService.getAll();
        int estadoFinal = all.size();

        assertEquals(estadoInicial-1, estadoFinal);
    }

    @Test
    @DisplayName("Deve atualizar uma consulta existente no banco.")
    public void testarUpdate() {
        int id_consulta = 3;
        Consulta consultaParaAtualizar = consultaService.getById(id_consulta);
        consultaParaAtualizar.setDiagnostico("Dengue");
        consultaService.update(id_consulta, consultaParaAtualizar);

        Consulta consultaAtualizada = consultaService.getById(id_consulta);

        assertEquals("Dengue", consultaAtualizada.getDiagnostico());
    }

    @Test
    @DisplayName("Deve buscar uma consulta por ID.")
    public void testarGetById() {
        int id_consulta = 2;

        Consulta consulta = new Consulta();
        consulta.setId(id_consulta);
        consulta.setData(LocalDate.of(2024, 6, 18));
        consulta.setPaciente(null);
        consulta.setMedico(null);
        consulta.setConsultaFinalizada(true);
        consulta.setDiagnostico("Frescura");
        consultaService.save(consulta);

        Consulta consultaCadastrada = consultaService.getById(id_consulta);

        assertEquals(consulta.getId(), consultaCadastrada.getId());
        assertEquals(consulta.getDiagnostico(), consultaCadastrada.getDiagnostico());
    }
}
