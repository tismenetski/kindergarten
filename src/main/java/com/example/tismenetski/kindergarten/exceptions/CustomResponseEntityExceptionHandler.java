package com.example.tismenetski.kindergarten.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice // A mechanism that gives you global exception handler
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    //Exception For Worker Not Found
    @ExceptionHandler
    public final ResponseEntity<Object> handleWorkerNotFoundException(WorkerNotFoundException ex, WebRequest request)
    {
        WorkerNotFoundExceptionResponse exceptionResponse = new WorkerNotFoundExceptionResponse(ex.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex, WebRequest request)
    {
        UsernameAlreadyExistsExceptionResponse exceptionResponse = new UsernameAlreadyExistsExceptionResponse(ex.getMessage());

        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }


}
