package com.fernando.sprinboot.restaurant.proyect.restaurant.mappers;

import org.mapstruct.Mapper;

import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.extraService.ExtraServiceDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.extraService.ExtraServiceRequestDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.extraService.ExtraServiceShortInfoDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.interfaces.BaseMapper;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.ExtraService;

@Mapper(componentModel = "spring")
public interface ExtraServicesMapper extends BaseMapper<ExtraService, ExtraServiceDto> {
    
    ExtraService fromBodyToEntity(ExtraServiceRequestDto dto);

    ExtraServiceShortInfoDto toShortInfoDto(ExtraService extraService);
}
