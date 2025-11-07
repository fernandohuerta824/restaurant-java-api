package com.fernando.sprinboot.restaurant.proyect.restaurant.mappers;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.extraService.ExtraServiceDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.extraService.ExtraServiceRequestDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.extraService.ExtraServiceShortInfoDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.interfaces.BaseMapper;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.ExtraService;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ExtraServicesMapper extends BaseMapper<ExtraService, ExtraServiceDto> {
    
    ExtraService fromBodyToEntity(ExtraServiceRequestDto dto);

    ExtraServiceShortInfoDto toShortInfoDto(ExtraService extraService);

    void updateProductFromDto(ExtraServiceRequestDto dto, @MappingTarget ExtraService entity);
}
