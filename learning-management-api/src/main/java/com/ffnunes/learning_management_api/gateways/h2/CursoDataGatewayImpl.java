package com.ffnunes.learning_management_api.gateways.h2;

import com.ffnunes.learning_management_api.domain.Curso;
import com.ffnunes.learning_management_api.gateways.CursoDataGateway;
import com.ffnunes.learning_management_api.gateways.h2.entities.CursoEntity;
import com.ffnunes.learning_management_api.gateways.h2.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CursoDataGatewayImpl implements CursoDataGateway {

    private final CursoRepository repository;

    @Override
    public Curso save(final Curso curso) {
        return repository.save(CursoEntity.create(curso)).toDomain();
    }

    @Override
    public Optional<Curso> findByIdAndDeletado(final Long id, final boolean deletado) {
        return repository.findByIdAndDeletado(id, deletado).map(CursoEntity::toDomain);
    }

    @Override
    public Optional<Curso> findByNomeAndDeletado(final String nome, final boolean deletado) {
        return repository.findByNomeAndDeletado(nome, deletado).map(CursoEntity::toDomain);
    }

    @Override
    public List<Curso> findAll() {
        return repository.findAll().stream().map(CursoEntity::toDomain).toList();
    }

    @Override
    public List<Curso> findAllByIds(final List<Long> ids) {
        return repository.findAllById(ids).stream().map(CursoEntity::toDomain).toList();
    }

}
