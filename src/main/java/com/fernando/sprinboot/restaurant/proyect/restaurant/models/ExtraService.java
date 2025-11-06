package com.fernando.sprinboot.restaurant.proyect.restaurant.models;

import java.math.BigDecimal;

import com.fernando.sprinboot.restaurant.proyect.restaurant.constants.FieldLengths;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "extra_services")
public class ExtraService {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = FieldLengths.SHORT_NAME, nullable = false)
    private String name;

    @Column(length = FieldLengths.DESCRIPTION)
    private String description;

    private BigDecimal price;


    public ExtraService(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;

    }

    public ExtraService(String name, String description, BigDecimal price) {
        this(null, name, description, price);
    }

    public ExtraService(String name, BigDecimal price) {
        this(name, "", price);
    }

    public ExtraService() {
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

}
