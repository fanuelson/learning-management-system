package com.ffnunes.learning_management_api.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Data
@With
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Estudante {
    private Long id;
    private String email;
    private String primeiroNome;
    private String ultimoNome;
    private String telefone;
    private LocalDate dataNascimento;

    public int calcularIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
