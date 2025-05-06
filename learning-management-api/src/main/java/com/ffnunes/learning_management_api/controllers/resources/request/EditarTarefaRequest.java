package com.ffnunes.learning_management_api.controllers.resources.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EditarTarefaRequest {

    @NotBlank
    private String descricao;

    //TODO: toDomain
}
