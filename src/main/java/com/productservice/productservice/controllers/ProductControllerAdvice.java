package com.productservice.productservice.controllers;

import com.productservice.productservice.dtos.ExceptionDto;
import com.productservice.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> productNotFoundException(ProductNotFoundException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exceptionDto, exceptionDto.getHttpStatus());
    }
}
