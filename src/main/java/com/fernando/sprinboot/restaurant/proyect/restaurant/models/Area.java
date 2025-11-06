package com.fernando.sprinboot.restaurant.proyect.restaurant.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.fernando.sprinboot.restaurant.proyect.restaurant.constants.FieldLengths;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "areas")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = FieldLengths.SHORT_NAME)
    private String name;

    @Column(length = FieldLengths.DESCRIPTION)
    private String description;

    private Boolean available;
    

    @ManyToOne
    @JoinColumn(name = "parent_area_id")
    private Area parentArea;

    @OneToMany(mappedBy = "parentArea", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Area> childrenAreas = new HashSet<>();

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<RestaurantTable> tables = new HashSet<>();

    public Area() {
    }

    public Area(String name, String description, Boolean available, Area parentArea) {
        this.name = name;
        this.description = description;
        this.available = available;
        this.parentArea = parentArea;
    }

    public Area(Long id, String name, String description, Boolean available, Area parentArea) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
        this.parentArea = parentArea;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Area getParentArea() {
        return parentArea;
    }

    public void setParentArea(Area parentArea) {
        this.parentArea = parentArea;
    }

    public Set<Area> getChildrenAreas() {
        return Collections.unmodifiableSet(childrenAreas);
    }

    public void addChildrenArea(Area area) {
        childrenAreas.add(area);
    }

    public void removeChildrenArea(Area area) {
        childrenAreas.remove(area);
    }

    public Set<RestaurantTable> getTables() {
        return Collections.unmodifiableSet(tables);
    }

    public void addTable(RestaurantTable table) {
        tables.add(table);
    }

    public void removeTable(RestaurantTable table) {
        tables.remove(table);
    }

    
}
