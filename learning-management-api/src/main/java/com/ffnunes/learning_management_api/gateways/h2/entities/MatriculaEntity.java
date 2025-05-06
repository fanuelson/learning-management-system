package com.ffnunes.learning_management_api.gateways.h2.entities;

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

}
