package com.ffnunes.learning_management_api.gateways.h2;

import com.ffnunes.learning_management_api.domain.Matricula;
import com.ffnunes.learning_management_api.gateways.MatriculaDataGateway;
import com.ffnunes.learning_management_api.gateways.h2.entities.MatriculaEntity;
import com.ffnunes.learning_management_api.gateways.h2.repositories.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MatriculaDataGatewayImpl implements MatriculaDataGateway {

    private final MatriculaRepository repository;

    @Override
    public List<Matricula> findAllByEstudanteId(final Long estudanteId) {
        return repository.findByIdEstudanteId(estudanteId).stream().map(MatriculaEntity::toDomain).toList();
    }

    @Override
    public void save(final Matricula matricula) {
        repository.save(MatriculaEntity.create(matricula));
    }
}
