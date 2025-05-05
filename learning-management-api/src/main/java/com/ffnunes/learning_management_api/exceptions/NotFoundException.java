package com.ffnunes.learning_management_api.exceptions;

public class NotFoundException extends RuntimeException {

  public NotFoundException(final String message) {
    super(message);
  }
}
