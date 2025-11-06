package com.fernando.sprinboot.restaurant.proyect.restaurant.dto.restaurantTable;

import com.fernando.sprinboot.restaurant.proyect.restaurant.constants.FieldLengths;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnCreate;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnUpdate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RestaurantTableRequestDto {
    @NotBlank(message = "The name is required", groups = {OnCreate.class})
    @Size(max = FieldLengths.SHORT_NAME, message = "The name cannot be greather than " + FieldLengths.NAME + " characters", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @Min(value = 1, message = "The capacity cannot be smaller than 1", groups = {OnCreate.class, OnUpdate.class})
    @Max(value = 10, message = "The capacity cannot be greather than 10", groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message = "The capacity is required", groups = {OnCreate.class})
    private Integer capacity;

    @Size(max = FieldLengths.DESCRIPTION, message = "The description cannot be greather than " + FieldLengths.DESCRIPTION + " characters", groups = {OnCreate.class, OnUpdate.class})
    private String description;
    
    @NotNull(message = "The area is required", groups = {OnCreate.class})
    private Long areaId;

    @NotNull(message = "The availability is required", groups = {OnCreate.class})
    private Boolean available;

    public RestaurantTableRequestDto() {
    }

    public RestaurantTableRequestDto(
            @NotBlank(message = "The name is required") @Size(max = 30, message = "The name cannot be greather than 80 characters") String name,
            @Min(value = 1, message = "The capacity cannot be smaller than 1") @Max(value = 10, message = "The capacity cannot be greather than 10") Integer capacity,
            @Size(max = 255, message = "The description cannot be greather than 255 characters") String description,
            @NotNull(message = "The area is required") Long areaId,
            Boolean available
            ) {
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.areaId = areaId;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    
}
