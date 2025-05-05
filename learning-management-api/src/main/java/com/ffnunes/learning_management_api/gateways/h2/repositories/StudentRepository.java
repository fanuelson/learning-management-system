package com.ffnunes.learning_management_api.gateways.h2.repositories;

import com.ffnunes.learning_management_api.gateways.h2.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByEmail(final String email);
}
