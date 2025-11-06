package com.fernando.sprinboot.restaurant.proyect.restaurant.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.ExtraService;

public interface ExtraSevicesRepository extends JpaRepository<ExtraService, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
