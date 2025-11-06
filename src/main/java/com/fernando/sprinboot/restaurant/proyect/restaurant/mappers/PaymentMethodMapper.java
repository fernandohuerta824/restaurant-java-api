package com.fernando.sprinboot.restaurant.proyect.restaurant.mappers;

import org.mapstruct.Mapper;

import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.paymentMethod.PaymentMethodDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.interfaces.BaseMapper;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.PaymentMethod;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper extends BaseMapper<PaymentMethod, PaymentMethodDto> {

    
}
