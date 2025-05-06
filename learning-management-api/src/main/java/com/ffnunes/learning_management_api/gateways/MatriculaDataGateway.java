package com.ffnunes.learning_management_api.gateways;

import com.ffnunes.learning_management_api.domain.Matricula;

import java.util.List;

public interface MatriculaDataGateway {
    void save(final Matricula matricula);
    List<Matricula> findAllByEstudanteId(final Long estudanteId);
}
