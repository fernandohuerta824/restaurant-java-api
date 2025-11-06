package com.fernando.sprinboot.restaurant.proyect.restaurant.controllers.v1;

import org.springframework.data.domain.Page;
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
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.restaurantTable.RestaurantTableDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.restaurantTable.RestaurantTableRequestDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnCreate;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnUpdate;
import com.fernando.sprinboot.restaurant.proyect.restaurant.response.ApiListResponse;
import com.fernando.sprinboot.restaurant.proyect.restaurant.response.ApiResponse;
import com.fernando.sprinboot.restaurant.proyect.restaurant.services.RestaurantTableService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/restaurant-table")
@Tag(name = "Tables", description = "Tables management endpoints")
public class RestaurantTableController {
    
    private RestaurantTableService restaurantTableService;

    public RestaurantTableController(
        RestaurantTableService restaurantTableService
    ) {
        this.restaurantTableService = restaurantTableService;
    }

    @GetMapping
    public ResponseEntity<ApiListResponse<RestaurantTableDto>> getAll(
        @RequestParam(required = false, defaultValue = "1") Integer page,
        @RequestParam(required = false, defaultValue = "1") Integer minCapacity,
        @RequestParam(required = false, defaultValue = "10") Integer maxCapacity,
        @RequestParam(required = false, defaultValue = "true") Boolean available

    ) {
        Page<RestaurantTableDto> tables = restaurantTableService.findAll(page, minCapacity, maxCapacity, available);

        return BuildResponse.buildResponse(
            "All tables retrieved successfully", 
            HttpStatus.OK, 
            tables
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantTableDto>> getById(@PathVariable Long id) {
        RestaurantTableDto table = restaurantTableService.findById(id);

        return BuildResponse.buildResponse(
            "Table retrieved successfully", 
            HttpStatus.OK, 
            table
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RestaurantTableDto>> save(
        @Validated(OnCreate.class) @RequestBody RestaurantTableRequestDto dto
    ) {
        RestaurantTableDto table = restaurantTableService.save(dto);

        return BuildResponse.buildResponse(
            "Table created successfully", 
            HttpStatus.CREATED, 
            table
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantTableDto>> update(
        @Validated(OnUpdate.class) @RequestBody RestaurantTableRequestDto dto,
        @PathVariable Long id
    ) {
        RestaurantTableDto table = restaurantTableService.update(dto, id);

        return BuildResponse.buildResponse(
            "Table updated successfully", 
            HttpStatus.OK, 
            table
        );
    }
}
