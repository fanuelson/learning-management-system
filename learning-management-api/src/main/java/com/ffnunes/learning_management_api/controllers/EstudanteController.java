package com.ffnunes.learning_management_api.controllers;

import com.ffnunes.learning_management_api.controllers.resources.request.CriarEstudanteRequest;
import com.ffnunes.learning_management_api.controllers.resources.request.CriarTarefaRequest;
import com.ffnunes.learning_management_api.controllers.resources.response.CriarEstudanteResponse;
import com.ffnunes.learning_management_api.domain.Matricula;
import com.ffnunes.learning_management_api.usecases.CriarEstudante;
import com.ffnunes.learning_management_api.usecases.MatriculaService;
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
    private final MatriculaService matriculaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CriarEstudanteResponse criar(@RequestBody @Valid final CriarEstudanteRequest body) {
        final var estudante = criarEstudante.execute(body.toDomain());
        return CriarEstudanteResponse.create(estudante);
    }

    @PostMapping("/{estudanteId}/cursos/{cursoId}/matricular")
    @ResponseStatus(HttpStatus.CREATED)
    public void matricular(
            @PathVariable final Long estudanteId,
            @PathVariable final Long cursoId) {
        matriculaService.matricular(
                Matricula.builder()
                        .estudanteId(estudanteId)
                        .cursoId(cursoId)
                        .build()
        );
    }

    @PostMapping("/{estudanteId}/cursos/{cursoId}/tarefas")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarTarefa(
            @PathVariable final Long estudanteId,
            @PathVariable final Long cursoId,
            @RequestBody @Valid final CriarTarefaRequest body) {
        final var matricula = Matricula.builder()
                .estudanteId(estudanteId)
                .cursoId(cursoId)
                .build();
        //TODO: tarefaService.criar(matricula, tarefa)
        // retornar idTarefa
    }

}
