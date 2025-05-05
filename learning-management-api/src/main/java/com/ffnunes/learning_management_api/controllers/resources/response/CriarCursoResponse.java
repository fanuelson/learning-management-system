package com.ffnunes.learning_management_api.controllers.resources.response;

import com.ffnunes.learning_management_api.domain.Curso;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CriarCursoResponse {

    private Long id;

    public static CriarCursoResponse create(final Curso curso) {
        return CriarCursoResponse.builder()
                .id(curso.getId())
                .build();
    }
}
