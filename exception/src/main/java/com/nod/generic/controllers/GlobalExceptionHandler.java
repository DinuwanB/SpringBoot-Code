package com.nod.generic.controllers;

import com.nod.generic.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity handleCustomException(final CustomException customException){
        return new ResponseEntity<>("Deff No Risks here!", HttpStatus.I_AM_A_TEAPOT);
    }

}
