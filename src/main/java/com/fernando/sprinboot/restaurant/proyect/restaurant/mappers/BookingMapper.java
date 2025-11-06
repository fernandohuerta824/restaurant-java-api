package com.fernando.sprinboot.restaurant.proyect.restaurant.mappers;

import org.mapstruct.Mapper;

import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.booking.BookingDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.booking.BookingRequestDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.interfaces.BaseMapper;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.Booking;

@Mapper(componentModel = "spring", uses = {
    RestaurantTableMapper.class,
    BookingServiceMapper.class
})
public interface BookingMapper extends BaseMapper<Booking, BookingDto> {
    
    Booking fromBodyToEntity(BookingRequestDto dto);

}
