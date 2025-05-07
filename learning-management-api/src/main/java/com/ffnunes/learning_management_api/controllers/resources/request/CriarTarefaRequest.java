package com.ffnunes.learning_management_api.controllers.resources.request;

import com.ffnunes.learning_management_api.domain.Tarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CriarTarefaRequest {

    @NotNull
    private LocalDate data;

    @NotBlank
    private String categoriaTarefa;

    @NotBlank
    private String descricao;

    public Tarefa toDomain() {
        return Tarefa.builder()
                .data(data)
                .categoriaTarefa(categoriaTarefa)
                .descricao(descricao)
                .build();
    }
}
