package com.ffnunes.learning_management_api.controllers;

import com.ffnunes.learning_management_api.controllers.resources.request.CreateStudentRequest;
import com.ffnunes.learning_management_api.domain.Student;
import com.ffnunes.learning_management_api.usecases.CreateStudent;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final CreateStudent createStudent;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody @Valid final CreateStudentRequest body) {
        log.info("Create student body: {}", body);
        return createStudent.execute(body.toDomain());
    }
}
