package com.ffnunes.learning_management_api.domain;

import lombok.*;

@Data
@With
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Matricula {
    private Long idEstudante;
    private Long idCurso;
}
