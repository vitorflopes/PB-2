package com.example.prescricao_service.repository;

import com.example.prescricao_service.model.Prescricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescricaoRepository extends JpaRepository<Prescricao, Integer> {
    Optional<Prescricao> findByConsultaId(Integer consultaId);
}
