package com.ffnunes.learning_management_api.gateways;

import com.ffnunes.learning_management_api.domain.CategoriaTarefa;

import java.util.List;

public interface CategoriaTarefaDataGateway {
    List<CategoriaTarefa> findAll();
}
