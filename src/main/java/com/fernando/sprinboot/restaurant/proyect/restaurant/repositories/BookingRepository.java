package com.fernando.sprinboot.restaurant.proyect.restaurant.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fernando.sprinboot.restaurant.proyect.restaurant.models.Booking;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("""
        SELECT 
            b
        FROM Booking b
        WHERE 
            b.date BETWEEN :minDate AND :maxDate
            AND b.startTime >= :startTime
            AND b.status = :status
        ORDER BY b.date, b.startTime
    """)
    List<Booking> findBooking(
        @Param("minDate") LocalDate minDate,
        @Param("maxDate") LocalDate maxDate,
        @Param("startTime") LocalTime startTime,
        @Param("status") BookingStatus status
    );

    @Query("""
        SELECT COUNT(b)
        FROM Booking b
        WHERE 
            b.date BETWEEN :minDate AND :maxDate
            AND b.startTime >= :startTime
            AND b.status = :status
    """)
    long countBooking(
        @Param("minDate") LocalDate minDate,
        @Param("maxDate") LocalDate maxDate,
        @Param("startTime") LocalTime startTime,
        @Param("status") BookingStatus status
    );


    @Query("""
        SELECT COUNT(b) > 0
        FROM Booking b
        JOIN b.tables t
        WHERE 
            t.id = :tableId
            AND b.date = :date
            AND (b.startTime < :endTime AND b.endTime > :startTime)
    """)
    boolean isTableNotAvailable(
        Long tableId,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime
    );

}

