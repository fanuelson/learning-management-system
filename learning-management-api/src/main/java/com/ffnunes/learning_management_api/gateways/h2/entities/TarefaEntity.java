package com.ffnunes.learning_management_api.gateways.h2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarefas")
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

    //TODO: dúvida, pq dateTime? Como assim registrar de 30 em 30 min?
//    @Column(name = "tempo_gasto")
//    private LocalDateTime tempoGasto;
}
