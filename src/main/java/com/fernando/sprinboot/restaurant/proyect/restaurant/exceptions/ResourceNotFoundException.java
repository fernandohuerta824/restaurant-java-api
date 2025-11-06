package com.fernando.sprinboot.restaurant.proyect.restaurant.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
