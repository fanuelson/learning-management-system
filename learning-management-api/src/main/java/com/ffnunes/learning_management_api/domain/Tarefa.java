package com.ffnunes.learning_management_api.domain;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Data
@With
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Tarefa {
    private Long id;
    private Matricula matricula;
    private String categoriaTarefa;
    private LocalDate data;
    private String descricao;
    private LocalTime tempoGasto;

    public Tarefa merge(final Tarefa tarefa) {
        descricao = StringUtils.isBlank(tarefa.getDescricao()) ? descricao : tarefa.getDescricao();
        return this;
    }

}
