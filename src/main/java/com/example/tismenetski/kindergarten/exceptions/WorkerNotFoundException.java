package com.example.tismenetski.kindergarten.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WorkerNotFoundException extends RuntimeException {

    public WorkerNotFoundException(String message) {
        super(message);
    }
}
