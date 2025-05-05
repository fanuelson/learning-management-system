package com.ffnunes.learning_management_api.gateways.h2.repositories;

import com.ffnunes.learning_management_api.gateways.h2.entities.EstudanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudanteRepository extends JpaRepository<EstudanteEntity, Long> {
    Optional<EstudanteEntity> findByEmail(final String email);
}
