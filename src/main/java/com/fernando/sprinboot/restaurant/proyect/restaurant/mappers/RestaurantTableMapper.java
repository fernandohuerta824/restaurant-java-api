package com.fernando.sprinboot.restaurant.proyect.restaurant.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.restaurantTable.RestaurantTableDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.restaurantTable.RestaurantTableRequestDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.restaurantTable.RestaurantTableShortInfoDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.interfaces.BaseMapper;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.Area;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.RestaurantTable;

@Mapper(componentModel = "spring", uses = AreaMapper.class)
public interface RestaurantTableMapper extends BaseMapper<RestaurantTable, RestaurantTableDto>  {
    @Mapping(source = "areaId", target = "area", qualifiedByName = "mapAreaById")
    @Mapping(target = "id", ignore = true)
    RestaurantTable toEntity(RestaurantTableRequestDto dto);

    RestaurantTableShortInfoDto toShortInfoDto(RestaurantTable entity);

    @Named("mapAreaById")
    default Area mapArea(Long id) {
        if (id == null) return null;
        Area area = new Area();
        area.setId(id);
        return area;
    }

}
