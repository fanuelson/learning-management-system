package com.ffnunes.learning_management_api.gateways.h2;

import com.ffnunes.learning_management_api.domain.Estudante;
import com.ffnunes.learning_management_api.gateways.EstudanteDataGateway;
import com.ffnunes.learning_management_api.gateways.h2.entities.EstudanteEntity;
import com.ffnunes.learning_management_api.gateways.h2.repositories.EstudanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EstudanteDataGatewayImpl implements EstudanteDataGateway {

    private final EstudanteRepository repository;

    public Estudante save(final Estudante estudante) {
        return repository.save(EstudanteEntity.create(estudante)).toDomain();
    }

    public Optional<Estudante> findByEmail(final String email) {
        return repository.findByEmail(email).map(EstudanteEntity::toDomain);
    }
}
