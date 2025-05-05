package com.ffnunes.learning_management_api.domain;

import lombok.*;

import java.time.LocalDate;

@Data
@With
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Student {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private LocalDate birthDate;
}
