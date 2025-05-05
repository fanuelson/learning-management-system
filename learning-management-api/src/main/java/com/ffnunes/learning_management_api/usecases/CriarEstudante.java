package com.ffnunes.learning_management_api.usecases;

import com.ffnunes.learning_management_api.domain.Estudante;
import com.ffnunes.learning_management_api.gateways.EstudanteDataGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarEstudante {

    public static final int IDADE_MINIMA = 16;
    private final EstudanteDataGateway estudanteDataGateway;

    public Estudante execute(final Estudante estudante) {
        validarIdadeMinima(estudante);
        validarEmailExistente(estudante);
        return estudanteDataGateway.save(estudante.withId(null));
    }

    private void validarIdadeMinima(final Estudante estudante) {
        if (estudante.calcularIdade() < IDADE_MINIMA) {
            throw new IllegalArgumentException("Idade mínima para criação de estudantes é 16 anos");
        }
    }

    private void validarEmailExistente(final Estudante estudante) {
        estudanteDataGateway.findByEmail(estudante.getEmail())
                .ifPresent(existingStudent -> {
                    throw new IllegalArgumentException("Estudante já registrado com esse email");
                });
    }
}
