package com.example.usuario_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data@AllArgsConstructor@NoArgsConstructor@Builder
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    @Column(name = "is_doctor")
    private boolean isDoctor;
    private String especialidade;
    @ElementCollection
    private List<Integer> consultas;
}
