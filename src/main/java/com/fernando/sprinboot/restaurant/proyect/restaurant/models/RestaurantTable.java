package com.fernando.sprinboot.restaurant.proyect.restaurant.models;

import com.fernando.sprinboot.restaurant.proyect.restaurant.constants.FieldLengths;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurant_tables")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = FieldLengths.SHORT_NAME)
    private String name;

    private Integer capacity;

    @Column(length = FieldLengths.DESCRIPTION)
    private String description;
    
    private Boolean available;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    
    public RestaurantTable(String name, Integer capacity, String description, Boolean available, Area area) {
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.available = available;
        this.area = area;
    }

    public RestaurantTable(Long id, String name, Integer capacity, String description, Boolean available, Area area) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.available = available;
        this.area = area;
    }

    public RestaurantTable(String name, Integer capacity) {
        this(name, capacity, "", true, null);
    }

    public RestaurantTable() {
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


    public Area getArea() {
        return area;
    }


    public void setArea(Area area) {
        this.area = area;
        area.addTable(this);
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RestaurantTable other = (RestaurantTable) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}