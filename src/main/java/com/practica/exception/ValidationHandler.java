package com.practica.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ArrayList<String> listaErrores = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            listaErrores.add(error.getDefaultMessage());
        });

        ErrorDTO errorDTO = new ErrorDTO(listaErrores.toString().replace("[", "").replace("]",""), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<Object>(errorDTO, HttpStatus.BAD_REQUEST);

    }
}
