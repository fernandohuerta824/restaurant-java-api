package com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area;


public class AreaShortInfoDto {
    private Long id;
    private String name;
    private Long parentAreaId;
    private Boolean available;


    public AreaShortInfoDto() {
    }

    public AreaShortInfoDto(Long id, String name, Long parentAreaId ) {
        this.id = id;
        this.name = name;
        this.parentAreaId = parentAreaId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getParentAreaId() {
        return parentAreaId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentAreaId(Long parentAreaId) {
        this.parentAreaId = parentAreaId;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }


    
}
