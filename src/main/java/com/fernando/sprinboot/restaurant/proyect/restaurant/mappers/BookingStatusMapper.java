package com.fernando.sprinboot.restaurant.proyect.restaurant.mappers;

import org.mapstruct.Mapper;

import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.bookingStatus.BookingStatusDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.interfaces.BaseMapper;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.BookingStatus;

@Mapper(componentModel = "spring")
public interface BookingStatusMapper extends BaseMapper<BookingStatus, BookingStatusDto> {

}
