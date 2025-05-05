package com.ffnunes.learning_management_api.gateways.h2.entities;


import com.ffnunes.learning_management_api.domain.Estudante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "estudante")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstudanteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "primeiro_nome")
    private String primeiroNome;

    @Column(name = "ultimo_nome")
    private String ultimoNome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    public static EstudanteEntity create(final Estudante estudante) {
        return EstudanteEntity.builder()
                .id(estudante.getId())
                .email(estudante.getEmail())
                .primeiroNome(estudante.getPrimeiroNome())
                .ultimoNome(estudante.getUltimoNome())
                .telefone(estudante.getTelefone())
                .dataNascimento(estudante.getDataNascimento())
                .build();
    }

    public Estudante toDomain() {
        return Estudante.builder()
                .id(id)
                .email(email)
                .primeiroNome(primeiroNome)
                .ultimoNome(ultimoNome)
                .telefone(telefone)
                .dataNascimento(dataNascimento)
                .build();
    }
}
