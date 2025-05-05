package com.ffnunes.learning_management_api.gateways.h2.repositories;

import com.ffnunes.learning_management_api.gateways.h2.entities.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<CursoEntity, Long> {

    Optional<CursoEntity> findByIdAndDeletado(final Long id, final boolean deletado);

    Optional<CursoEntity> findByNomeAndDeletado(final String nome, final boolean deletado);
}
