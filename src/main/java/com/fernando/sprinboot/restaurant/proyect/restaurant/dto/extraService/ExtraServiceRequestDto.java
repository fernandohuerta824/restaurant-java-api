package com.fernando.sprinboot.restaurant.proyect.restaurant.dto.extraService;

import java.math.BigDecimal;

import com.fernando.sprinboot.restaurant.proyect.restaurant.constants.FieldLengths;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnCreate;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnUpdate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ExtraServiceRequestDto {
    @NotBlank(message = "The name is required", groups = {OnCreate.class})
    @Size(max = FieldLengths.NAME, message = "The name must be at most {max} characters long", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @Size(max = FieldLengths.DESCRIPTION, message = "The description must be at most {max} characters long", groups = {OnCreate.class, OnUpdate.class})
    private String description;

    @NotNull(message = "The price is required", groups = {OnCreate.class})
    @Positive(message = "The price must be positive", groups = {OnCreate.class, OnUpdate.class})
    @Max(value = 99_999, message = "The price must be at most {value}", groups = {OnCreate.class, OnUpdate.class})
    private BigDecimal price;

    public ExtraServiceRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    
}
