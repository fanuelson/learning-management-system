package com.ffnunes.learning_management_api.gateways.h2.entities;

import com.ffnunes.learning_management_api.domain.CategoriaTarefa;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "categoria_tarefa")
@AllArgsConstructor
@Data
public class CategoriaTarefaEntity {

    @Id
    private String nome;

    public CategoriaTarefa toDomain() {
        return new CategoriaTarefa(nome);
    }

    public static CategoriaTarefaEntity create(final CategoriaTarefa categoriaTarefa) {
        return new CategoriaTarefaEntity(categoriaTarefa.getNome());
    }
}
