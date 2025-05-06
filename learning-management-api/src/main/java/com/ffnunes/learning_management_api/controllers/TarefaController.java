package com.ffnunes.learning_management_api.controllers;

import com.ffnunes.learning_management_api.controllers.resources.request.EditarTarefaRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editar(
            @PathVariable final Long id,
            @RequestBody @Valid final EditarTarefaRequest body) {
        //TODO: tarefaService.editar(id, body.toDomain())
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remover(@PathVariable final Long id) {
        //TODO: tarefaService.remover(id);
    }

    @PatchMapping("/{id}/incrementar-tempo-gasto")
    @ResponseStatus(HttpStatus.OK)
    public void incrementarTempoGasto(@PathVariable final Long id) {
        //TODO: tarefaService.incrementarTempoGasto(id, 30)
    }

}
