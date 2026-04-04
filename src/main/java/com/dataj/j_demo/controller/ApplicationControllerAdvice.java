package com.dataj.j_demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.dataj.j_demo.exception.ApiErros;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleValidationException(
        MethodArgumentNotValidException
        argumentNotValidException)
    {
        List<String> list = argumentNotValidException
        .getBindingResult()
        .getAllErrors().stream().map(e-> 
            e.getDefaultMessage()
        ).collect(Collectors.toList());

        return new ApiErros(list);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException(ResponseStatusException ex)
    {
        String msg = ex.getMessage();
        HttpStatusCode codHttpStatus = ex.getStatusCode();
        ApiErros apiErros = new ApiErros(msg);

        return new ResponseEntity<>(apiErros, codHttpStatus);
    }

}
