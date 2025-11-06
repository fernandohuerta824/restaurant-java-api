package com.fernando.sprinboot.restaurant.proyect.restaurant.dto.extraService;


public class ExtraServiceShortInfoDto {
    private Long id;
    private String name;

    public ExtraServiceShortInfoDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
