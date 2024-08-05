package com.example.tp2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private boolean isDoctor;
    private String especialidade;

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultasPaciente;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultasMedico;
}