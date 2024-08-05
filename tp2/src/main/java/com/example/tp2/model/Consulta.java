package com.example.tp2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "id")
    private Usuario paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico", referencedColumnName = "id")
    private Usuario medico;

    private Boolean consultaFinalizada;

    private String diagnostico;
}
