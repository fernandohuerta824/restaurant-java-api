package com.fernando.sprinboot.restaurant.proyect.restaurant.mappers;


import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.restaurantTable.RestaurantTableDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.restaurantTable.RestaurantTableRequestDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.restaurantTable.RestaurantTableShortInfoDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.interfaces.BaseMapper;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.Area;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.RestaurantTable;

@Mapper(componentModel = "spring", uses = AreaMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RestaurantTableMapper extends BaseMapper<RestaurantTable, RestaurantTableDto>  {

    RestaurantTable toEntity(RestaurantTableRequestDto dto, @Context Area area);
    
    void updateEntity(
        RestaurantTableRequestDto dto, 
        @MappingTarget RestaurantTable entity,
        @Context Area area
    );

    @AfterMapping
    default void mapArea(
        RestaurantTableRequestDto dto, 
        @MappingTarget RestaurantTable entity, 
        @Context Area area
    ) {
        entity.setArea(area);
    }


    RestaurantTableShortInfoDto toShortInfoDto(RestaurantTable entity);

}
