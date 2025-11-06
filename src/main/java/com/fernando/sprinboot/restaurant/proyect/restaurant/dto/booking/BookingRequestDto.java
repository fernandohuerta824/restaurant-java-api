package com.fernando.sprinboot.restaurant.proyect.restaurant.dto.booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnCreate;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnUpdate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BookingRequestDto {

    @NotBlank(message = "Customer name is required", groups = {OnCreate.class})
    private String customerName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Date is required", groups = {OnCreate.class})
    private LocalDate date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @NotNull(message = "Start time is required", groups = {OnCreate.class})
    private LocalTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @NotNull(message = "End time is required", groups = {OnCreate.class})
    private LocalTime endTime;

    @NotEmpty(message = "At least one table ID is required", groups = {OnCreate.class, OnUpdate.class})
    private Set<@NotNull(message = "Table ID cannot be null", groups = {OnCreate.class, OnUpdate.class}) Long> tableIds = new HashSet<>();

    @NotNull(message = "Services set cannot be null", groups = {OnCreate.class, OnUpdate.class})
    private Set<@NotNull(message = "Service ID cannot be null", groups = {OnCreate.class, OnUpdate.class}) BookingServiceRequestDto> services = new HashSet<>();

    public BookingRequestDto() {
        this.tableIds = new HashSet<>();
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

    public Set<Long> getTableIds() {
        return tableIds;
    }

    public void setTableIds(Set<Long> tableIds) {
        this.tableIds = tableIds;
    }

    public Set<BookingServiceRequestDto> getServices() {
        return services;
    }

    public void setServices(Set<BookingServiceRequestDto> services) {
        this.services = services;
    }
}
