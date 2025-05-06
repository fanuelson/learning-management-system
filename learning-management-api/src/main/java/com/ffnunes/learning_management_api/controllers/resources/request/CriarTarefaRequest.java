package com.ffnunes.learning_management_api.controllers.resources.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CriarTarefaRequest {

    @NotNull
    private LocalDate data;

    @NotBlank
    private String categoria;

    @NotBlank
    private String descricao;

    //TODO: toDomain
}
