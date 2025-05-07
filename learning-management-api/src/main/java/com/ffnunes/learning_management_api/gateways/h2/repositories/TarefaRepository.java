package com.ffnunes.learning_management_api.gateways.h2.repositories;

import com.ffnunes.learning_management_api.gateways.h2.entities.MatriculaId;
import com.ffnunes.learning_management_api.gateways.h2.entities.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<TarefaEntity, Long> {

    List<TarefaEntity> findByMatriculaId(final MatriculaId matriculaId);
}
