package com.ffnunes.learning_management_api.gateways;

import com.ffnunes.learning_management_api.domain.Student;

import java.util.Optional;

public interface StudentDataGateway {
    Student save(final Student student);

    Optional<Student> findByEmail(final String email);
}
