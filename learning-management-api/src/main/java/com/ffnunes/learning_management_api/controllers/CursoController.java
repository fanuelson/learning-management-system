package com.ffnunes.learning_management_api.controllers;

import com.ffnunes.learning_management_api.controllers.resources.request.CriarCursoRequest;
import com.ffnunes.learning_management_api.controllers.resources.request.EditarCursoRequest;
import com.ffnunes.learning_management_api.controllers.resources.response.CriarCursoResponse;
import com.ffnunes.learning_management_api.domain.Curso;
import com.ffnunes.learning_management_api.usecases.CursoService;
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
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Curso> findAll() {
        return cursoService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Curso findById(@PathVariable Long id) {
        return cursoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CriarCursoResponse criar(@RequestBody @Valid final CriarCursoRequest body) {
        final var curso = cursoService.criar(body.toDomain());
        return CriarCursoResponse.create(curso);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CriarCursoResponse editar(
            @PathVariable final Long id,
            @RequestBody @Valid final EditarCursoRequest body) {
        final var curso = cursoService.editar(id, body.toDomain());
        return CriarCursoResponse.create(curso);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remover(@PathVariable final Long id) {
        cursoService.remover(id);
    }
}
