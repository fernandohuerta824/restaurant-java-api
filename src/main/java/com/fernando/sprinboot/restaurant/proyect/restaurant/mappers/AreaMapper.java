package com.fernando.sprinboot.restaurant.proyect.restaurant.mappers;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.*;

import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaFullInfoDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaRequestDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaShortInfoDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaTreeDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.interfaces.BaseMapper;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.Area;

@Mapper(componentModel = "spring")
public interface AreaMapper extends BaseMapper<Area, AreaFullInfoDto> {
    @Override
    @Mapping(target = "parentArea", ignore = true)
    AreaFullInfoDto toDto(Area entity);

    @Mapping(target = "parentArea", ignore = true)
    @Mapping(target = "tables", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "childrenAreas", ignore = true)
    Area fromBodyToEntity(AreaRequestDto body);

    @Mapping(target = "parentAreaId", expression = "java(mapParentAreaId(entity))")
    AreaShortInfoDto toShortInfoDto(Area entity);

    List<AreaShortInfoDto> toListShortInfoDto(List<Area> entities);

    @Mapping(target = "childrenAreas", ignore = true)
    AreaTreeDto toTreeDto(Area entity);

    default AreaTreeDto setChildrenAreas(Area entity) {
        if(entity == null) {
            return null;
        }


        Set<AreaTreeDto> children = entity.getChildrenAreas()
            .stream()
            .map(this::setChildrenAreas)
            .collect(Collectors.toSet());

        return new AreaTreeDto(entity.getId(), entity.getName(), children);
    }

    @AfterMapping
    default void mapParentDto(Area entity, @MappingTarget AreaFullInfoDto dto) {
        if (entity.getParentArea() == null) return;

        Long grandParentId = entity.getParentArea().getParentArea() != null
            ? entity.getParentArea().getParentArea().getId()
            : null;

        dto.setParentArea(
            new AreaShortInfoDto(
                entity.getParentArea().getId(),
                entity.getParentArea().getName(),
                grandParentId
            )
        );
    }
    
    @Named("mapParentAreaId")
    default Long mapParentAreaId(Area area) {
        if (area.getParentArea() == null) {
            return null;
        }
        return area.getParentArea().getId();
    
    }
}