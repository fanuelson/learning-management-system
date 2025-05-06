package com.ffnunes.learning_management_api.usecases;

import com.ffnunes.learning_management_api.domain.Matricula;
import com.ffnunes.learning_management_api.gateways.MatriculaDataGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    public static final int LIMITE_MAXIMO_MATRICULAS = 3;
    private final MatriculaDataGateway matriculaDataGateway;
    private final CursoService cursoService;

    public void matricular(final Matricula matricula) {
        List<Matricula> matriculas = matriculaDataGateway.findAllByEstudanteId(matricula.getEstudanteId());
        if (matriculas.size() >= LIMITE_MAXIMO_MATRICULAS) {
            throw new IllegalArgumentException("Limite máximo de 3 cursos matriculados atingido");
        }

        matriculaDataGateway.save(matricula);
    }
}
