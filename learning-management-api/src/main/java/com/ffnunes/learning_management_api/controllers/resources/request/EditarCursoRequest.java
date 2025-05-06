package com.ffnunes.learning_management_api.controllers.resources.request;

import com.ffnunes.learning_management_api.domain.Curso;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EditarCursoRequest {

    private String nome;

    @FutureOrPresent
    private LocalDate dataInicio;

    @FutureOrPresent
    private LocalDate dataConclusao;

    public Curso toDomain() {
        return Curso.builder()
                .nome(nome)
                .dataInicio(dataInicio)
                .dataConclusao(dataConclusao)
                .build();
    }
}
