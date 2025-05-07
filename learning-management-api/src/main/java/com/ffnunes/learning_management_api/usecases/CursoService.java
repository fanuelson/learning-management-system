package com.ffnunes.learning_management_api.usecases;

import com.ffnunes.learning_management_api.domain.Curso;
import com.ffnunes.learning_management_api.exceptions.NotFoundException;
import com.ffnunes.learning_management_api.gateways.CursoDataGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoDataGateway cursoDataGateway;

    public Curso criar(final Curso curso) {
        validateNomeExists(curso.getNome());
        final var dataConclusao = curso.getDataInicio().plusMonths(6);
        return cursoDataGateway.save(
                curso.withId(null).withDataConclusao(dataConclusao).withDeletado(false)
        );
    }

    public Curso editar(final Long id, final Curso curso) {
        validateNomeExists(curso.getNome());
        final var cursoSalvo = findById(id);
        return cursoDataGateway.save(cursoSalvo.merge(curso));
    }

    public void remover(final Long idCurso) {
        final var cursoSalvo = findById(idCurso);
        cursoDataGateway.save(
                cursoSalvo.withDeletado(true)
        );
    }

    public Curso findById(final Long id) {
        return cursoDataGateway
                .findByIdAndDeletado(id, false)
                .orElseThrow(() -> new NotFoundException("Curso com id: %s não encontrado".formatted(id)));
    }

    public List<Curso> findAll() {
        return this.cursoDataGateway.findAll();
    }

    public List<Curso> findAllByIds(final List<Long> ids) {
        return this.cursoDataGateway.findAllByIds(ids);
    }

    private void validateNomeExists(final String nome) {
        cursoDataGateway.findByNomeAndDeletado(nome, false)
                .ifPresent(cursoSalvo -> {
                    throw new IllegalArgumentException("Curso já registrado com esse nome");
                });
    }
}
