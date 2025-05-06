package com.ffnunes.learning_management_api.gateways.h2.entities;


import com.ffnunes.learning_management_api.domain.Curso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "curso")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_conclusao")
    private LocalDate dataConclusao;

    @Column(name = "deletado")
    private boolean deletado;

    public static CursoEntity create(final Curso curso) {
        return CursoEntity.builder()
                .id(curso.getId())
                .nome(curso.getNome())
                .dataInicio(curso.getDataInicio())
                .dataConclusao(curso.getDataConclusao())
                .deletado(curso.isDeletado())
                .build();
    }

    public Curso toDomain() {
        return Curso.builder()
                .id(id)
                .nome(nome)
                .dataInicio(dataInicio)
                .dataConclusao(dataConclusao)
                .deletado(deletado)
                .build();
    }
}
