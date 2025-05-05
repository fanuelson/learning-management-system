package com.ffnunes.learning_management_api.domain;

import lombok.*;

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
}
