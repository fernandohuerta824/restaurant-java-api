package com.fernando.sprinboot.restaurant.proyect.restaurant.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fernando.sprinboot.restaurant.proyect.restaurant.models.Area;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.RestaurantTable;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long>{
    boolean existsByNameAndIdNot(String name, Long id);
    boolean existsByName(String name);

    Optional<RestaurantTable> findByArea(Area area);

    Page<RestaurantTable> findByArea(Area area, Pageable pageable);

    Page<RestaurantTable> findByCapacityBetweenAndAvailable(Integer minCapacity, Integer maxCapacity, Boolean available, Pageable pageable);

    long countByCapacityBetweenAndAvailable(Integer minCapacity, Integer maxCapacity, Boolean available);

    long countByArea(Area area);
}
