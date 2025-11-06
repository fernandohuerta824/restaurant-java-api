package com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area;

import com.fernando.sprinboot.restaurant.proyect.restaurant.constants.FieldLengths;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnCreate;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnUpdate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AreaRequestDto {
    @NotBlank(message = "The name cannot be empty", groups = {OnCreate.class})
    @Size(max = FieldLengths.SHORT_NAME, message = "The name cannot be longer than 20", groups = {OnCreate.class, OnUpdate.class})
    private String name;


    @Size(max = FieldLengths.DESCRIPTION, groups = {OnCreate.class, OnUpdate.class})
    private String description;

    @NotNull(message = "The availability is required", groups = {OnCreate.class})
    private Boolean available;

    private Long parentAreaId;

    public AreaRequestDto(String name) {
        this(name, "name", true, null);
    }

    public AreaRequestDto(
            @NotBlank(message = "The name cannot be empty") @Size(max = 20, message = "The name cannot be longer than 20") String name,
            @Size(max = 255) String description, Boolean available, Long parentAreaId) {
        this.name = name;
        this.description = description;
        this.available = available == null ? false : available;
        this.parentAreaId = parentAreaId;
    }

    public AreaRequestDto() {
        
    }

    public void setName(String name) {
        this.name = name != null ? name.trim() : name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description != null ? description.trim() : description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Long getParentAreaId() {
        return parentAreaId;
    }

    public void setParentAreaId(Long parentAreaId) {
        this.parentAreaId = parentAreaId;
    }


    public String getName() {
        return name;
    }   

}
