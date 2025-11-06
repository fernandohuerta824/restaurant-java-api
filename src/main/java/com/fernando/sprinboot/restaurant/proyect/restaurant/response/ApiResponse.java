package com.fernando.sprinboot.restaurant.proyect.restaurant.response;

import java.time.Instant;

public class ApiResponse<T> {
    private String message;
    private T data;
    private int status;
    private Instant timestamp;
    
    public ApiResponse(String message, T data, int status, Instant timestamp) {
        this.message = message;
        this.data = data;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
 
    
}
