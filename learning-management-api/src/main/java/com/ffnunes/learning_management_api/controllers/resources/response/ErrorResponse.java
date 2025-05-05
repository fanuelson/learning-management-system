package com.ffnunes.learning_management_api.controllers.resources.response;

import lombok.Getter;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
public class ErrorResponse {

    private final List<String> errors;

    public ErrorResponse(@NonNull final List<String> errors) {
        this.errors = errors;
    }

    public ErrorResponse(@NonNull final String error) {
        this.errors = List.of(error);
    }
}
