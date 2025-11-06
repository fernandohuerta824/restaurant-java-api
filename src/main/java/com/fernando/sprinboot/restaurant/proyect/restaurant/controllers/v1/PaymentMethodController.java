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
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.paymentMethod.PaymentMethodDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.response.ApiListResponse;
import com.fernando.sprinboot.restaurant.proyect.restaurant.response.ApiResponse;
import com.fernando.sprinboot.restaurant.proyect.restaurant.services.PaymentMethodService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/payment-method")
@Tag(name = "Payment Method", description = "Payment Method management endpoints")
public class PaymentMethodController {
    
    private PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping
    @Operation(summary = "Get all payment methods", description = "Retrieve a list of all payment methods")
    public ResponseEntity<ApiListResponse<PaymentMethodDto>> getAll(
        @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        Page<PaymentMethodDto> paymentMethods = this.paymentMethodService.findAll(page);

        return BuildResponse.buildResponse(
            "All payment methods retrieved successfully", 
            HttpStatus.OK, 
            paymentMethods
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get payment method by ID", description = "Retrieve a payment method by its ID")
    public ResponseEntity<ApiResponse<PaymentMethodDto>> getById(@PathVariable Long id) {
        PaymentMethodDto paymentMethod = this.paymentMethodService.findById(id);

        return BuildResponse.buildResponse(
            "Payment method retrieved successfully", 
            HttpStatus.OK, 
            paymentMethod
        );
    }
}
