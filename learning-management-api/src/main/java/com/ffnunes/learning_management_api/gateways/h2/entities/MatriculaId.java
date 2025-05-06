package com.ffnunes.learning_management_api.gateways.h2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class MatriculaId implements Serializable {

    @Column(name = "estudante_id")
    private Long estudanteId;

    @Column(name = "curso_id")
    private Long cursoId;
}
