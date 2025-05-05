package com.ffnunes.learning_management_api.controllers.resources.request;

import com.ffnunes.learning_management_api.domain.Curso;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EditarCursoRequest {

    @NotBlank
    private String nome;

    @FutureOrPresent
    private LocalDate dataInicio;

    private boolean concluido;

    public Curso toDomain() {
        return Curso.builder()
                .nome(nome)
                .dataInicio(dataInicio)
                .concluido(concluido)
                .build();
    }
}
