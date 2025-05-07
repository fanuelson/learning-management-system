package com.ffnunes.learning_management_api.controllers.resources.request;

import com.ffnunes.learning_management_api.domain.Tarefa;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EditarTarefaRequest {

    @NotBlank
    private String descricao;

    public Tarefa toDomain() {
        return Tarefa.builder().descricao(descricao).build();
    }

}
