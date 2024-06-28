package com.example.spring_jdbc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler
    public ResponseEntity<ManagerHandler> managerHandlerResponseEntity(ManagerException exception) {
        ManagerHandler managerHandler = new ManagerHandler(exception.getMessage());
        return new ResponseEntity<>(managerHandler, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ManagerHandler> managerNotFound(ManagerNotFoundException e) {
        ManagerHandler managerHandler = new ManagerHandler(e.getMessage());
        return new ResponseEntity<>(managerHandler, HttpStatus.NOT_FOUND);
    }
}
