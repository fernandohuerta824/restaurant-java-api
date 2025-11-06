package com.fernando.sprinboot.restaurant.proyect.restaurant.controllers.v1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.sprinboot.restaurant.proyect.restaurant.controllers.BuildResponse;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.booking.BookingDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.booking.BookingRequestDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnCreate;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnUpdate;
import com.fernando.sprinboot.restaurant.proyect.restaurant.response.ApiResponse;
import com.fernando.sprinboot.restaurant.proyect.restaurant.services.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/booking")
@Tag(name = "Booking", description = "Booking management endpoints")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    @Operation(summary = "Get all bookings", description = "Retrieves a paginated list of all bookings")
    public List<BookingDto> getAll(
        @RequestParam(required  = false, defaultValue = "1") Integer page,
        @RequestParam(required = false) LocalDate minDate,
        @RequestParam(required = false) LocalDate maxDate,
        @RequestParam(required = false) LocalTime startTime,
        @RequestParam(required = false) Long statusId
    ) {
        LocalDate today = LocalDate.now();
        if(minDate == null) {
            minDate = LocalDate.of(today.getYear(), today.getMonthValue() - 1, today.getDayOfMonth());
            System.out.println("minDate: " + minDate);
        }

        if(maxDate == null) {
            maxDate = LocalDate.of(today.getYear(), today.getMonthValue() + 1, today.getDayOfMonth());
            System.out.println("maxDate: " + maxDate);
        }


        if(startTime == null) {
            startTime = LocalTime.of(0, 0, 0);
            System.out.println("startTime: " + startTime);
        }

        List<BookingDto> bookings = bookingService.findAll(minDate, maxDate, startTime, statusId);

        return bookings;
    }

    @PostMapping
    @Operation(summary = "Create a new booking", description = "Creates a new booking with the provided details")
    public ResponseEntity<ApiResponse<BookingDto>> save(
        @Validated(OnCreate.class) @RequestBody BookingRequestDto body
    ) {
        BookingDto createdBooking = bookingService.save(body);
        
        return BuildResponse.buildResponse(
            "Booking created successfully", 
            HttpStatus.CREATED, 
            createdBooking
        );
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update an existing booking", description = "Updates the details of an existing booking")
    public ResponseEntity<ApiResponse<BookingDto>> update(
        @PathVariable Long id,
        @Validated(OnUpdate.class) @RequestBody BookingRequestDto body
    ) {
        BookingDto updatedBooking = bookingService.update(id, body);
        return BuildResponse.buildResponse(
            "Booking updated successfully",
            HttpStatus.OK,
            updatedBooking
        );
    }
}
