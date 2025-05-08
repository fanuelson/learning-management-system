package com.ffnunes.learning_management_api.gateways.h2.entities;

import com.ffnunes.learning_management_api.domain.Tarefa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tarefa")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private MatriculaId matriculaId;

    @OneToOne
    @JoinColumn(name = "categoria_tarefa", referencedColumnName = "nome")
    private CategoriaTarefaEntity categoriaTarefa;

    private LocalDate data;

    private String descricao;

    @Column(name = "tempo_gasto")
    private LocalTime tempoGasto;


    public static TarefaEntity create(final Tarefa tarefa) {
        final var matriculaId = MatriculaId.builder()
                .estudanteId(tarefa.getMatricula().getEstudanteId())
                .cursoId(tarefa.getMatricula().getCursoId())
                .build();
        return TarefaEntity.builder()
                .id(tarefa.getId())
                .matriculaId(matriculaId)
                .categoriaTarefa(new CategoriaTarefaEntity(tarefa.getCategoriaTarefa()))
                .data(tarefa.getData())
                .descricao(tarefa.getDescricao())
                .tempoGasto(tarefa.getTempoGasto())
                .build();
    }

    public Tarefa toDomain() {
        return Tarefa.builder()
                .id(id)
                .matricula(matriculaId.toDomain())
                .categoriaTarefa(categoriaTarefa.getNome())
                .data(data)
                .descricao(descricao)
                .tempoGasto(tempoGasto)
                .build();
    }
}
