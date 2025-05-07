package com.ffnunes.learning_management_api.gateways.h2;

import com.ffnunes.learning_management_api.domain.Matricula;
import com.ffnunes.learning_management_api.domain.Tarefa;
import com.ffnunes.learning_management_api.gateways.TarefaDataGateway;
import com.ffnunes.learning_management_api.gateways.h2.entities.MatriculaId;
import com.ffnunes.learning_management_api.gateways.h2.entities.TarefaEntity;
import com.ffnunes.learning_management_api.gateways.h2.repositories.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TarefaDataGatewayImpl implements TarefaDataGateway {

    private final TarefaRepository repository;

    @Override
    public List<Tarefa> findAllByMatriculaId(final Matricula matricula) {
        return repository.findByMatriculaId(MatriculaId.create(matricula)).stream().map(TarefaEntity::toDomain).toList();
    }

    @Override
    public Tarefa save(final Tarefa tarefa) {
        return repository.save(TarefaEntity.create(tarefa)).toDomain();
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public Optional<Tarefa> findById(final Long id) {
        return this.repository.findById(id).map(TarefaEntity::toDomain);
    }
}
