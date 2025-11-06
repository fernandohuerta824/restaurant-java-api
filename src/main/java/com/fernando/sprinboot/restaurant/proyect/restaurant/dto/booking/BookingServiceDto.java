package com.fernando.sprinboot.restaurant.proyect.restaurant.dto.booking;

import java.math.BigDecimal;

import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.extraService.ExtraServiceShortInfoDto;

public class    BookingServiceDto {
    private Long id;

    private ExtraServiceShortInfoDto extraService;

    private Integer quantity;

    private BigDecimal price;

    public BookingServiceDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExtraServiceShortInfoDto getExtraService() {
        return extraService;
    }

    public void setExtraService(ExtraServiceShortInfoDto extraService) {
        this.extraService = extraService;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    
}
