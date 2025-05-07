package com.ffnunes.learning_management_api.gateways;

import com.ffnunes.learning_management_api.domain.Matricula;
import com.ffnunes.learning_management_api.domain.Tarefa;

import java.util.List;
import java.util.Optional;

public interface TarefaDataGateway {

    List<Tarefa> findAllByMatriculaId(final Matricula matricula);

    Optional<Tarefa> findById(final Long id);

    Tarefa save(final Tarefa tarefa);

    void delete(final Long id);
}
