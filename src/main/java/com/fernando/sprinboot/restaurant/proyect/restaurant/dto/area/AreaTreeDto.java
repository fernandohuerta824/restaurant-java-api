package com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area;

import java.util.HashSet;
import java.util.Set;

public class AreaTreeDto {
    private Long id;
    private String name;
    private Set<AreaTreeDto> childrenAreas = new HashSet<>();

    public AreaTreeDto() {
    }

    public AreaTreeDto(Long id, String name, Set<AreaTreeDto> childrenAreas) {
        this.id = id;
        this.name = name;
        this.childrenAreas = childrenAreas;
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

    public Set<AreaTreeDto> getChildrenAreas() {
        return childrenAreas;
    }

    public void setChildrenAreas(Set<AreaTreeDto> childrenAreas) {
        this.childrenAreas = childrenAreas;
    }

    

}
