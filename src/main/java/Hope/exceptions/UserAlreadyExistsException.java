package com.example.hope.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends ResponseStatusException {
    public UserAlreadyExistsException(String username) {
        super(HttpStatus.CONFLICT, String.format("User with username '%s' already exists", username));
    }
}
