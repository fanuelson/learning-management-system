package com.ffnunes.learning_management_api.gateways.h2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria_tarefa")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriaTarefaEntity {

    @Id
    private String nome;

}
