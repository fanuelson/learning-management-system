package com.ffnunes.learning_management_api.gateways.h2;

import com.ffnunes.learning_management_api.gateways.CategoriaTarefaDataGateway;
import com.ffnunes.learning_management_api.gateways.h2.entities.CategoriaTarefaEntity;
import com.ffnunes.learning_management_api.gateways.h2.repositories.CategoriaTarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoriaTarefaDataGatewayImpl implements CategoriaTarefaDataGateway {

    private final CategoriaTarefaRepository repository;


    @Override
    public List<String> findAll() {
        return repository.findAll().stream().map(CategoriaTarefaEntity::getNome).toList();
    }
}
