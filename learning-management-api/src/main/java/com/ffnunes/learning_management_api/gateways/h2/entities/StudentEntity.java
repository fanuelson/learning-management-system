package com.ffnunes.learning_management_api.gateways.h2.entities;


import com.ffnunes.learning_management_api.domain.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    public static StudentEntity create(final Student student) {
        return StudentEntity.builder()
                .id(student.getId())
                .email(student.getEmail())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .phone(student.getPhone())
                .birthDate(student.getBirthDate())
                .build();
    }

    public Student toDomain() {
        return Student.builder()
                .id(id)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .birthDate(birthDate)
                .build();
    }
}
