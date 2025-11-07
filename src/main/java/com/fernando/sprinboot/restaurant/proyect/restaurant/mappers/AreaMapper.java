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

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AreaMapper extends BaseMapper<Area, AreaFullInfoDto> {

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

    Area toEntity(AreaRequestDto body, @Context Area parentArea);

    @AfterMapping
    default void mapArea(
        AreaRequestDto dto, 
        @MappingTarget Area area,
        @Context Area parentArea
    ) {
        area.setParentArea(parentArea);
    }

    void updateEntity(
        AreaRequestDto dto,
        @MappingTarget Area entity,
        @Context Area parentArea
    );

    AreaShortInfoDto toShortInfoDto(Area entity);

    @AfterMapping
    default void mapParentAreaId(Area entity, @MappingTarget AreaShortInfoDto dto) {
        if (entity.getParentArea() == null) {
            dto.setParentAreaId(null);
            return;
        }
        
        dto.setParentAreaId(entity.getParentArea().getId());
    }

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

}