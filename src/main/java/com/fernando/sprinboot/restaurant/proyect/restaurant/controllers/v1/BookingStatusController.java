package com.fernando.sprinboot.restaurant.proyect.restaurant.controllers.v1;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.sprinboot.restaurant.proyect.restaurant.controllers.BuildResponse;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.bookingStatus.BookingStatusDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.response.ApiListResponse;
import com.fernando.sprinboot.restaurant.proyect.restaurant.response.ApiResponse;
import com.fernando.sprinboot.restaurant.proyect.restaurant.services.BookingStatusService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/booking-status")
@Tag(name = "Booking Status", description = "Booking Status management endpoints")
public class BookingStatusController {
    private final BookingStatusService bookingStatusService;

    public BookingStatusController(BookingStatusService bookingStatusService) {
        this.bookingStatusService = bookingStatusService;
    }

    @GetMapping
    @Operation(summary = "Get all booking status", description = "Retrieve a list of all booking status")
    public ResponseEntity<ApiListResponse<BookingStatusDto>> getAll(
        @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        Page<BookingStatusDto> status = this.bookingStatusService.findAll(page);

        return BuildResponse.buildResponse(
            "All booking status retrieved successfully", 
            HttpStatus.OK, 
            status
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get booking status by ID", description = "Retrieve a booking status by its ID")
    public ResponseEntity<ApiResponse<BookingStatusDto>> getById(@PathVariable Long id) {
        BookingStatusDto status = this.bookingStatusService.findById(id);
        
        return BuildResponse.buildResponse("Booking status retrieved successfully", HttpStatus.OK, status);
    }
    
}
