package com.practica.exception.handler;

import com.practica.exception.ErrorDTO;
import com.practica.exception.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RequestException.class})
    public ResponseEntity<?>requestExceptionHandler(final RequestException ex){
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?>handleUnknownException(final Exception ex){
        ErrorDTO errorDTO = new ErrorDTO("Intente más tarde", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
