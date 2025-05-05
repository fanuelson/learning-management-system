package com.ffnunes.learning_management_api.controllers.resources.response;

import com.ffnunes.learning_management_api.domain.Estudante;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CriarEstudanteResponse {

    private Long id;

    public static CriarEstudanteResponse create(final Estudante estudante) {
        return CriarEstudanteResponse.builder()
                .id(estudante.getId())
                .build();
    }
}
