package com.ffnunes.learning_management_api.controllers.resources.request;

import com.ffnunes.learning_management_api.domain.Estudante;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CriarEstudanteRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String primeiroNome;

    @NotBlank
    private String ultimoNome;

    @NotBlank
    private String telefone;

    @Past
    private LocalDate dataNascimento;

    public Estudante toDomain() {
        return Estudante.builder()
                .email(email)
                .primeiroNome(primeiroNome)
                .ultimoNome(ultimoNome)
                .telefone(telefone)
                .dataNascimento(dataNascimento)
                .build();
    }
}
