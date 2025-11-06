package com.fernando.sprinboot.restaurant.proyect.restaurant.dto.restaurantTable;

import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaFullInfoDto;

public class RestaurantTableDto {
    private Long id;

    private String name;

    private Integer capacity;

    private String description;

    private Boolean available;

    private AreaFullInfoDto area;

    public RestaurantTableDto(Long id, String name, Integer capacity, String description, Boolean available,
            AreaFullInfoDto area) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.available = available;
        this.area = area;
    }

    public RestaurantTableDto(String name, Integer capacity, String description, Boolean available,
            AreaFullInfoDto area) {
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.available = available;
        this.area = area;
    }

    public RestaurantTableDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public AreaFullInfoDto getArea() {
        return area;
    }

    public void setArea(AreaFullInfoDto area) {
        this.area = area;
    }
    
    
}
