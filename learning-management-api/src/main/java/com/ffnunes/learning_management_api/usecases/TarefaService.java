package com.ffnunes.learning_management_api.usecases;

import com.ffnunes.learning_management_api.domain.Tarefa;
import com.ffnunes.learning_management_api.exceptions.NotFoundException;
import com.ffnunes.learning_management_api.gateways.TarefaDataGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaDataGateway tarefaDataGateway;

    public Tarefa editar(final Long id, final Tarefa tarefa) {
        final var tarefaSalva = findById(id);
        return tarefaDataGateway.save(tarefaSalva.merge(tarefa));
    }

    public void delete(final Long id) {
        tarefaDataGateway.delete(id);
    }

    public Tarefa findById(final Long id) {
        return tarefaDataGateway.findById(id).orElseThrow(() -> new NotFoundException("Tarefa não encontrada"));
    }
}
