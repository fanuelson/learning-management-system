package com.ffnunes.learning_management_api.gateways.h2.entities;

import com.ffnunes.learning_management_api.domain.Matricula;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaId implements Serializable {

    @Column(name = "estudante_id")
    private Long estudanteId;

    @Column(name = "curso_id")
    private Long cursoId;

    public static MatriculaId create(final Matricula matricula) {
        return MatriculaId.builder()
                .estudanteId(matricula.getEstudanteId())
                .cursoId(matricula.getCursoId())
                .build();
    }

    public Matricula toDomain() {
        return Matricula.builder()
                .estudanteId(estudanteId)
                .cursoId(cursoId)
                .build();
    }
}
