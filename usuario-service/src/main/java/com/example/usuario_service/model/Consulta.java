package com.example.usuario_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "doctor_id")
    private int doctorId;
    @Column(name = "paciente_id")
    private int pacienteId;
    @Column(name = "consulta_marcada")
    private Boolean consultaMarcada;
    @Column(name = "consulta_Finalizada")
    private Boolean consultaFinalizada;

    private LocalDate data;
    private String paciente;
    private String diagnostico;
}
