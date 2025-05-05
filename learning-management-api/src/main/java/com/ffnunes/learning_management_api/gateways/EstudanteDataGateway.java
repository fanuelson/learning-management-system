package com.ffnunes.learning_management_api.gateways;

import com.ffnunes.learning_management_api.domain.Estudante;

import java.util.Optional;

public interface EstudanteDataGateway {
    Estudante save(final Estudante estudante);

    Optional<Estudante> findByEmail(final String email);
}
