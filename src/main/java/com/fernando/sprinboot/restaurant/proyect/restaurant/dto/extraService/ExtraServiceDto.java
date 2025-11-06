package com.fernando.sprinboot.restaurant.proyect.restaurant.dto.extraService;

import java.math.BigDecimal;

public class ExtraServiceDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    

    public ExtraServiceDto() {
    }

    public ExtraServiceDto(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    
    
}
