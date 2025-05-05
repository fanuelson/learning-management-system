package com.ffnunes.learning_management_api.gateways.h2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "estudante")
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//TODO: Mapear
public class MatriculaEntity {

    private Long idEstudante;
    private Long idCurso;
}
