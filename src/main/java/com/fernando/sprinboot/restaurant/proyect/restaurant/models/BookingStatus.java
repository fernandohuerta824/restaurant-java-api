package com.fernando.sprinboot.restaurant.proyect.restaurant.models;

import com.fernando.sprinboot.restaurant.proyect.restaurant.constants.FieldLengths;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking_status")
public class BookingStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = FieldLengths.SHORT_NAME)
    private String name;

    public BookingStatus(String name) {
        this.name = name;
    }

    public BookingStatus(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BookingStatus() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
