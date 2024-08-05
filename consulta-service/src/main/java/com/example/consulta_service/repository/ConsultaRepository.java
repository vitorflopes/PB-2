package com.example.consulta_service.repository;

import com.example.consulta_service.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
