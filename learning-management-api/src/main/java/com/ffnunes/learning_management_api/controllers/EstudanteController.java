package com.ffnunes.learning_management_api.controllers;

import com.ffnunes.learning_management_api.controllers.resources.request.CriarEstudanteRequest;
import com.ffnunes.learning_management_api.controllers.resources.request.CriarTarefaRequest;
import com.ffnunes.learning_management_api.controllers.resources.response.CriarEstudanteResponse;
import com.ffnunes.learning_management_api.domain.Curso;
import com.ffnunes.learning_management_api.domain.Estudante;
import com.ffnunes.learning_management_api.domain.Matricula;
import com.ffnunes.learning_management_api.domain.Tarefa;
import com.ffnunes.learning_management_api.usecases.EstudanteService;
import com.ffnunes.learning_management_api.usecases.MatriculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/estudantes")
@RequiredArgsConstructor
public class EstudanteController {

    private final EstudanteService estudanteService;
    private final MatriculaService matriculaService;

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.CREATED)
    public Estudante findByEmail(@PathVariable final String email) {
        return estudanteService.findByEmail(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CriarEstudanteResponse findByEmail(@RequestBody @Valid final CriarEstudanteRequest body) {
        final var estudante = estudanteService.criar(body.toDomain());
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

    @GetMapping("/{estudanteId}/cursos")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Curso> findCursosMatriculados(@PathVariable final Long estudanteId) {
        return matriculaService.findAllCursosMatriculados(estudanteId);
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
        matriculaService.criarTarefa(body.toDomain().withMatricula(matricula));
    }

    @GetMapping("/{estudanteId}/cursos/{cursoId}/tarefas")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Tarefa> findAllTarefas(
            @PathVariable final Long estudanteId,
            @PathVariable final Long cursoId) {
        final var matricula = Matricula.builder()
                .estudanteId(estudanteId)
                .cursoId(cursoId)
                .build();
        return matriculaService.findAllTarefas(matricula);
    }

}
