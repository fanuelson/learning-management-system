package com.ffnunes.learning_management_api.gateways.h2.repositories;

import com.ffnunes.learning_management_api.gateways.h2.entities.MatriculaEntity;
import com.ffnunes.learning_management_api.gateways.h2.entities.MatriculaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<MatriculaEntity, MatriculaId> {

    List<MatriculaEntity> findByIdEstudanteId(final Long estudanteId);
}
