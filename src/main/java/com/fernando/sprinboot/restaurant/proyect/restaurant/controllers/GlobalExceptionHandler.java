package com.fernando.sprinboot.restaurant.proyect.restaurant.controllers;

import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fernando.sprinboot.restaurant.proyect.restaurant.exceptions.BussinessException;
import com.fernando.sprinboot.restaurant.proyect.restaurant.exceptions.ResourceAlreadyExistsException;
import com.fernando.sprinboot.restaurant.proyect.restaurant.exceptions.ResourceNotFoundException;
import com.fernando.sprinboot.restaurant.proyect.restaurant.response.ApiErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(exception = ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse<String>> resourceNotFound(ResourceNotFoundException ex) {
        HttpStatusCode code = HttpStatusCode.valueOf(404);
        ApiErrorResponse<String> res = new ApiErrorResponse<String>(
            "Resource not found", 
            ex.getMessage(), 
            null, 
            code.value(), 
            Instant.now()
        );

        return ResponseEntity.status(code).body(res);
    }

    @ExceptionHandler(exception = ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse<String>> resourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        HttpStatusCode code = HttpStatusCode.valueOf(409);
        ApiErrorResponse<String> res = new ApiErrorResponse<String>(
            "Resource already exists", 
            ex.getMessage(), 
            null, 
            code.value(), 
            Instant.now()
        );

        return ResponseEntity.status(code).body(res);
    }

    @ExceptionHandler(exception = BussinessException.class)
    public ResponseEntity<ApiErrorResponse<String>> bussinessException(BussinessException ex) {
        HttpStatusCode code = HttpStatusCode.valueOf(400);
        ApiErrorResponse<String> res = new ApiErrorResponse<String>(
            "Bussiness Error", 
            ex.getMessage(), 
            null, 
            code.value(), 
            Instant.now()
        );

        return ResponseEntity.status(code).body(res);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse<List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        HttpStatusCode code = HttpStatusCode.valueOf(400);
        List<String> errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                                .toList();

        ApiErrorResponse<List<String>> res = new ApiErrorResponse<>(
            "Validation Error", 
            "One or more fields are not valid", 
            errors, 
            code.value(), 
            Instant.now()
        );
        return ResponseEntity.status(code).body(res);
    }
}
