package com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area;


public class AreaFullInfoDto {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private AreaShortInfoDto parentArea;

    public AreaFullInfoDto(Long id, String name, String description, Boolean available, AreaShortInfoDto parentArea) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.parentArea = parentArea;
    }

    public AreaFullInfoDto() {
        
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

    public Boolean getAvailable() {
        return available;
    }

    public AreaShortInfoDto getParentArea() {
        return parentArea;
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

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setParentArea(AreaShortInfoDto parentArea) {
        this.parentArea = parentArea;
    }

    
}
