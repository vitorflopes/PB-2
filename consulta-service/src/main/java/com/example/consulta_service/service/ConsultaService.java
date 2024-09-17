package com.example.consulta_service.service;

import com.example.consulta_service.DTO.PrescricaoDTO;
import com.example.consulta_service.DTO.UsuarioDTO;
import com.example.consulta_service.exception.ResourceNotFoundException;
import com.example.consulta_service.model.Consulta;
import com.example.consulta_service.repository.ConsultaRepository;
import com.example.consulta_service.service.clients.ConsultaClient;
import com.example.consulta_service.service.clients.EmailClient;
import com.example.consulta_service.service.clients.PrescricaoClient;
import com.example.consulta_service.service.clients.UsuarioClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultaService {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;
    private final ConsultaRepository consultaRepository;
    private final PrescricaoClient prescricaoClient; // Cliente para o serviço de prescrições
    private final EmailClient emailClient; //Client do serviço de email
    private final UsuarioClient usuarioClient;

    public Consulta create(Consulta consulta) throws JsonProcessingException {
        consulta = consultaRepository.save(consulta);

        amqpTemplate.convertAndSend(
                "consulta-exc",
                "rk-consulta",
                objectMapper.writeValueAsString(consulta)
        );

        return consulta;
    }

    public void mark(Consulta consulta) {
        consultaRepository.save(consulta);
    }

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }
    public Optional<Consulta> findById(Integer id) {
        if (!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta Não Localizada");
        }
        return consultaRepository.findById(id);
    }
    public void deleteById(Integer id) {
        if (!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário Não Localizado");
        }
        consultaRepository.deleteById(id);
    }
    public void update(Integer id, Consulta consulta) {
        if(!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta Não Localizado");
        }
        consulta.setId(id);
        consultaRepository.save(consulta);
    }

    public void finalizarConsulta(Integer id) {
        Optional<Consulta> consultaOpt = consultaRepository.findById(id);
        if (!consultaOpt.isPresent()) {
            throw new ResourceNotFoundException("Consulta Não Localizada");
        }

        Consulta consulta = consultaOpt.get();
        if (consulta.getConsultaFinalizada() != null && consulta.getConsultaFinalizada()) {
            throw new IllegalStateException("A consulta já está finalizada.");
        }

        boolean temPrescricao = prescricaoClient.verificarPrescricao(consulta.getId());
        if (!temPrescricao) {
            throw new IllegalStateException("A consulta não pode ser finalizada sem uma prescrição.");
        }

        PrescricaoDTO prescricao = prescricaoClient.obterPrescricaoPorConsultaId(consulta.getId());

        UsuarioDTO usuarioDTO = usuarioClient.getUsuarioById(consulta.getPacienteId());
        String email = usuarioDTO.getEmail();

        String corpoEmail = "Sua consulta foi finalizada. Detalhes da prescrição:\n\n"
                + "Medicamento" + prescricao.getMedicamento() + "\n"
                + "Instruções: " + prescricao.getInstrucoes() + "\n";

        consulta.setConsultaFinalizada(true);
        consultaRepository.save(consulta);
        emailClient.enviarEmail(email,"Consulta Finalizada", corpoEmail);
    }
}
