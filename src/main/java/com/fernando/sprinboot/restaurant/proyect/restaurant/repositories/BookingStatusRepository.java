package com.fernando.sprinboot.restaurant.proyect.restaurant.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernando.sprinboot.restaurant.proyect.restaurant.models.BookingStatus;

public interface BookingStatusRepository extends JpaRepository<BookingStatus, Long> {
    Optional<BookingStatus> findByName(String name);
}
