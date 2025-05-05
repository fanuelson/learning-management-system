package com.ffnunes.learning_management_api.usecases;

import com.ffnunes.learning_management_api.domain.Curso;
import com.ffnunes.learning_management_api.exceptions.NotFoundException;
import com.ffnunes.learning_management_api.gateways.CursoDataGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoDataGateway cursoDataGateway;

    public Curso criar(final Curso curso) {
        validateNomeExists(curso.getNome());
        return cursoDataGateway.save(
                curso.withId(null).withConcluido(false).withDeletado(false)
        );
    }

    public Curso editar(final Long id, final Curso curso) {
        final var cursoSalvo = findById(id);
        validateNomeExists(curso.getNome());
        return cursoDataGateway.save(cursoSalvo.merge(curso));
    }

    public void remover(final Long idCurso) {
        final var cursoSalvo = findById(idCurso);
        cursoDataGateway.save(
                cursoSalvo.withDeletado(true)
        );
    }

    private Curso findById(final Long id) {
        return cursoDataGateway
                .findByIdAndDeletado(id, false)
                .orElseThrow(() -> new NotFoundException("Curso com id: %s não encontrado".formatted(id)));
    }

    private void validateNomeExists(final String nome) {
        cursoDataGateway.findByNomeAndDeletado(nome, false)
                .ifPresent(cursoSalvo -> {
                    throw new IllegalArgumentException("Curso já registrado com esse nome");
                });
    }
}
