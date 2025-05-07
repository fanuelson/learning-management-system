package com.ffnunes.learning_management_api.usecases;

import com.ffnunes.learning_management_api.gateways.CategoriaTarefaDataGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriaTarefaService {

    private final CategoriaTarefaDataGateway categoriaTarefaDataGateway;

    public List<String> findAll() {
        return categoriaTarefaDataGateway.findAll();
    }

}
