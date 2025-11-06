package com.fernando.sprinboot.restaurant.proyect.restaurant.dto.restaurantTable;

public class RestaurantTableShortInfoDto {
    private Long id;

    private String name;

    private Integer capacity;

    private Boolean available;

    public RestaurantTableShortInfoDto(Long id, String name, Integer capacity, Boolean available) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.available = available;
    }

    
    public RestaurantTableShortInfoDto() {
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


    public Boolean getAvailable() {
        return available;
    }


    public void setAvailable(Boolean available) {
        this.available = available;
    }

    
}
