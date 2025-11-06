package com.fernando.sprinboot.restaurant.proyect.restaurant.mappers;

import org.mapstruct.Mapper;

import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.booking.BookingServiceDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.booking.BookingServiceRequestDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.interfaces.BaseMapper;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.BookingExtraService;

@Mapper(componentModel = "spring", uses = {ExtraServicesMapper.class})
public interface BookingServiceMapper extends BaseMapper<BookingExtraService, BookingServiceDto> {
    
    BookingServiceRequestDto toRequestDto(BookingExtraService entity);
}
