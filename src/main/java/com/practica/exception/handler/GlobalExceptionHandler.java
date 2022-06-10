package com.practica.exception.handler;

import com.practica.exception.ErrorDTO;
import com.practica.exception.RequestException;
import com.practica.exception.ValidationHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({RequestException.class})
    public ResponseEntity<?>requestExceptionHandler(final RequestException ex){
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage(), ex.getStatus().value());
        return new ResponseEntity(errorDTO, ex.getStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?>handleUnknownException(final Exception ex){
        ErrorDTO errorDTO = new ErrorDTO("Intente más tarde", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
