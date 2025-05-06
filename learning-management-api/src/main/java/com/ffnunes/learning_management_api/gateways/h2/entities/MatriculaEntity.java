package com.ffnunes.learning_management_api.gateways.h2.entities;

import com.ffnunes.learning_management_api.domain.Matricula;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matricula")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatriculaEntity {

    @EmbeddedId
    private MatriculaId id;

    public static MatriculaEntity create(final Matricula matricula) {
        final var matriculaId = MatriculaId.builder()
                .estudanteId(matricula.getEstudanteId())
                .cursoId(matricula.getCursoId())
                .build();
        return MatriculaEntity.builder().id(matriculaId).build();
    }

    public Matricula toDomain() {
        return Matricula.builder()
                .estudanteId(id.getEstudanteId())
                .cursoId(id.getCursoId())
                .build();
    }
}
