package com.fernando.sprinboot.restaurant.proyect.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernando.sprinboot.restaurant.proyect.restaurant.models.RestaurantTable;

public interface RestaurantTableRespository extends JpaRepository<RestaurantTable, Long>{
    
}
