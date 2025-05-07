package com.ffnunes.learning_management_api.gateways;

import com.ffnunes.learning_management_api.domain.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoDataGateway {
    Curso save(final Curso curso);

    Optional<Curso> findByIdAndDeletado(final Long id, final boolean deletado);

    Optional<Curso> findByNomeAndDeletado(final String nome, final boolean deletado);

    List<Curso> findAll();

    List<Curso> findAllByIds(final List<Long> ids);
}
