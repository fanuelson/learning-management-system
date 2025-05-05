package com.ffnunes.learning_management_api.domain;

import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

@Data
@With
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Curso {
    private Long id;
    private String nome;
    private LocalDate dataInicio;
    private boolean concluido;
    private boolean deletado;

    public Curso merge(final Curso curso) {
        nome = StringUtils.isBlank(curso.getNome()) ? nome : curso.getNome();
        dataInicio = ObjectUtils.defaultIfNull(curso.getDataInicio(), dataInicio);
        concluido = curso.concluido;
        return this;
    }

}
