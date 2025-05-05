package com.ffnunes.learning_management_api.usecases;

import com.ffnunes.learning_management_api.domain.Estudante;
import com.ffnunes.learning_management_api.gateways.EstudanteDataGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarEstudante {

    private final EstudanteDataGateway estudanteDataGateway;

    public Estudante execute(final Estudante estudante) {
        if (estudante.calcularIdade() < 16) {
            throw new IllegalArgumentException("Idade mínima para criação de estudantes é 16 anos");
        }
        estudanteDataGateway.findByEmail(estudante.getEmail())
                .ifPresent(existingStudent -> {
                    throw new IllegalArgumentException("Estudante já registrado com esse email");
                });
        return estudanteDataGateway.save(estudante.withId(null));
    }
}
