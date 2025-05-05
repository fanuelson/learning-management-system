package com.ffnunes.learning_management_api.usecases;

import com.ffnunes.learning_management_api.domain.Student;
import com.ffnunes.learning_management_api.gateways.StudentDataGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateStudent {

    private final StudentDataGateway studentDataGateway;

    public Student execute(final Student student) {
        //TODO: validate age >= 16
        //TODO: validate email exists
        studentDataGateway.findByEmail(student.getEmail())
                .ifPresent(existingStudent -> {
                    throw new IllegalArgumentException("Email already exists");
                });
        return studentDataGateway.save(student);
    }
}
