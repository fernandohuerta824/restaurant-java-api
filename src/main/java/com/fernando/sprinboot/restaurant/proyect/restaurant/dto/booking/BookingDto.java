package com.fernando.sprinboot.restaurant.proyect.restaurant.dto.booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import java.util.HashSet;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.restaurantTable.RestaurantTableDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.BookingStatus;

public class BookingDto {
    private Long id;
    private String customerName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;
    private BookingStatus status;

    private Set<RestaurantTableDto> tables = new HashSet<>();
    
    private Set<BookingServiceDto> extraServices = new HashSet<>();

    public BookingDto() {

    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Set<RestaurantTableDto> getTables() {
        return tables;
    }

    public void setTables(Set<RestaurantTableDto> tables) {
        this.tables = tables;
    }

    public Set<BookingServiceDto> getExtraServices() {
        return extraServices;
    }

    public void setExtraServices(Set<BookingServiceDto> extraServices) {
        this.extraServices = extraServices;
    }


}
