package com.ffnunes.learning_management_api.usecases;

import com.ffnunes.learning_management_api.domain.Estudante;
import com.ffnunes.learning_management_api.exceptions.NotFoundException;
import com.ffnunes.learning_management_api.gateways.EstudanteDataGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstudanteService {

    public static final int IDADE_MINIMA = 16;
    private final EstudanteDataGateway estudanteDataGateway;

    public Estudante criar(final Estudante estudante) {
        validarIdadeMinima(estudante);
        validarEmailExistente(estudante);
        return estudanteDataGateway.save(estudante.withId(null));
    }

    public Estudante findByEmail(final String email) {
        return estudanteDataGateway.findByEmail(email).orElseThrow(() -> new NotFoundException("Estudante não encontrado"));
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
