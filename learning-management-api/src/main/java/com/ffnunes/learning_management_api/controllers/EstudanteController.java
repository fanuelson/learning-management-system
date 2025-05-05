package com.ffnunes.learning_management_api.controllers;

import com.ffnunes.learning_management_api.controllers.resources.request.CriarEstudanteRequest;
import com.ffnunes.learning_management_api.controllers.resources.response.CriarEstudanteResponse;
import com.ffnunes.learning_management_api.usecases.CriarEstudante;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/estudantes")
@RequiredArgsConstructor
public class EstudanteController {

    private final CriarEstudante criarEstudante;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CriarEstudanteResponse criar(@RequestBody @Valid final CriarEstudanteRequest body) {
        final var estudante = criarEstudante.execute(body.toDomain());
        return CriarEstudanteResponse.create(estudante);
    }
}
