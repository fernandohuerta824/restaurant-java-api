package com.fernando.sprinboot.restaurant.proyect.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernando.sprinboot.restaurant.proyect.restaurant.models.BookingExtraService;

public interface BookingServiceRepository extends JpaRepository<BookingExtraService, Long> {
    
}
