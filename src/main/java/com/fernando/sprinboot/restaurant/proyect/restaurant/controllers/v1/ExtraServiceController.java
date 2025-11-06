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
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.extraService.ExtraServiceDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.extraService.ExtraServiceRequestDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnCreate;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnUpdate;
import com.fernando.sprinboot.restaurant.proyect.restaurant.response.ApiListResponse;
import com.fernando.sprinboot.restaurant.proyect.restaurant.response.ApiResponse;
import com.fernando.sprinboot.restaurant.proyect.restaurant.services.ExtraServiceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/api/v1/extra-service")
@Tag(name = "Extra Service", description = "Extra Service management endpoints")
public class ExtraServiceController {

    private ExtraServiceService extraServiceService;

    public ExtraServiceController(ExtraServiceService extraServiceService) {
        this.extraServiceService = extraServiceService;
    }

    @GetMapping
    @Operation(summary = "Get all extra services", description = "Retrieve a list of all extra services")
    public ResponseEntity<ApiListResponse<ExtraServiceDto>> getAll(
        @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        Page<ExtraServiceDto> extraServices = this.extraServiceService.findAll(page);

        return BuildResponse.buildResponse("All extra services retrieved successfully", HttpStatus.OK, extraServices);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get extra service by ID", description = "Retrieve an extra service by its ID")
    public ResponseEntity<ApiResponse<ExtraServiceDto>> getById(@PathVariable Long id) {
        ExtraServiceDto extraService = this.extraServiceService.findById(id);

        return BuildResponse.buildResponse("Extra service retrieved successfully", HttpStatus.OK, extraService);
    }

    @PostMapping
    @Operation(summary = "Create a new extra service", description = "Create a new extra service with the provided details")
    public ResponseEntity<ApiResponse<ExtraServiceDto>> save(
        @Validated(OnCreate.class) @RequestBody ExtraServiceRequestDto body
    ) {
        ExtraServiceDto service = extraServiceService.save(body);
        return BuildResponse.buildResponse("Extra service created successfully", HttpStatus.CREATED, service);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update an existing extra service", description = "Update the details of an existing extra service")
    public ResponseEntity<ApiResponse<ExtraServiceDto>> update(
        @PathVariable Long id,
        @Validated(OnUpdate.class) @RequestBody ExtraServiceRequestDto body
    ) {
        ExtraServiceDto updatedService = extraServiceService.update(id, body);
        return BuildResponse.buildResponse("Extra service updated successfully", HttpStatus.OK, updatedService);
    }
}
