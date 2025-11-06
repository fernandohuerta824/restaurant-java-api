package com.fernando.sprinboot.restaurant.proyect.restaurant.response;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse<T> extends ApiResponse<T>{
    private String name;
    
    public ApiErrorResponse(String name, String message, T data, int status, Instant timestamp) {
        super(message, data, status, timestamp);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
