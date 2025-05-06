package com.ffnunes.learning_management_api.controllers.resources.request;

import com.ffnunes.learning_management_api.domain.Curso;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CriarCursoRequest {

    @NotBlank
    private String nome;

    @FutureOrPresent
    @NotNull
    private LocalDate dataInicio;

    public Curso toDomain() {
        return Curso.builder()
                .nome(nome)
                .dataInicio(dataInicio)
                .build();
    }
}
