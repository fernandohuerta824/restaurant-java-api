package com.fernando.sprinboot.restaurant.proyect.restaurant.controllers;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fernando.sprinboot.restaurant.proyect.restaurant.response.ApiListResponse;
import com.fernando.sprinboot.restaurant.proyect.restaurant.response.ApiResponse;

public class BuildResponse {
    private BuildResponse() {}

    public static <T> ResponseEntity<ApiResponse<T>> buildResponse(String message, HttpStatus code, T data) {
        ApiResponse<T> res = new ApiResponse<>(message, data, code.value(), Instant.now());
        return ResponseEntity.status(code).body(res);
    }

    public static <T> ResponseEntity<ApiListResponse<T>> buildResponse(String message, HttpStatus code, Page<T> data) {
        ApiListResponse<T> res = new ApiListResponse<>(message, data, code.value(), Instant.now());
        return ResponseEntity.status(code).body(res);
    }
}
