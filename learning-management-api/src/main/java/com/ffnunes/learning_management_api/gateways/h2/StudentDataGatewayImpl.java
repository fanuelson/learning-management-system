package com.ffnunes.learning_management_api.gateways.h2;

import com.ffnunes.learning_management_api.domain.Student;
import com.ffnunes.learning_management_api.gateways.StudentDataGateway;
import com.ffnunes.learning_management_api.gateways.h2.entities.StudentEntity;
import com.ffnunes.learning_management_api.gateways.h2.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class StudentDataGatewayImpl implements StudentDataGateway {

    private final StudentRepository repository;

    public Student save(final Student student) {
        return repository.save(StudentEntity.create(student)).toDomain();
    }

    public Optional<Student> findByEmail(final String email) {
        return repository.findByEmail(email).map(StudentEntity::toDomain);
    }
}
