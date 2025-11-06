package com.fernando.sprinboot.restaurant.proyect.restaurant.dto.booking;

import java.math.BigDecimal;

import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnCreate;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnUpdate;

import jakarta.validation.constraints.NotNull;

public class BookingServiceRequestDto {

    @NotNull(message = "Extra service ID is required", groups = {OnCreate.class, OnUpdate.class})
    private Long id;


    private Integer quantity;

    private BigDecimal price;

    public BookingServiceRequestDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    
}
