package com.fernando.sprinboot.restaurant.proyect.restaurant.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fernando.sprinboot.restaurant.proyect.restaurant.constants.PaginationData;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.restaurantTable.RestaurantTableDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.restaurantTable.RestaurantTableRequestDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.restaurantTable.RestaurantTableShortInfoDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.exceptions.ResourceAlreadyExistsException;
import com.fernando.sprinboot.restaurant.proyect.restaurant.exceptions.ResourceNotFoundException;
import com.fernando.sprinboot.restaurant.proyect.restaurant.helpers.CorrectPage;
import com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.RestaurantTableMapper;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.Area;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.RestaurantTable;
import com.fernando.sprinboot.restaurant.proyect.restaurant.repositories.AreaRepository;
import com.fernando.sprinboot.restaurant.proyect.restaurant.repositories.RestaurantTableRepository;

@Service
public class RestaurantTableService {
    
    private RestaurantTableRepository restaurantTableRepository;
    private RestaurantTableMapper restaurantTableMapper;
    private AreaRepository areaRepository;

    public RestaurantTableService(
        RestaurantTableRepository restaurantTableRepository,
        RestaurantTableMapper restaurantTableMapper,
        AreaRepository areaRepository
    )  {
        this.restaurantTableRepository = restaurantTableRepository;
        this.restaurantTableMapper = restaurantTableMapper;
        this.areaRepository = areaRepository;
    }

    @Transactional(readOnly = true)
    public Page<RestaurantTableDto> findAll(
        Integer page,
        Integer minCapacity,
        Integer maxCapacity,
        Boolean available
    ) {
        page = CorrectPage.setCorrectPage(restaurantTableRepository.countByCapacityBetweenAndAvailable(minCapacity, maxCapacity, available), PaginationData.DEFAULT_PAGE_SIZE.getValue(), page);
        System.out.println(page);
        Page<RestaurantTable> tables = restaurantTableRepository.findByCapacityBetweenAndAvailable(minCapacity, maxCapacity, available, PageRequest.of(page - 1, PaginationData.DEFAULT_PAGE_SIZE.getValue()));

        return tables.map(restaurantTableMapper::toDto);
    }

    @Transactional 
    public RestaurantTableDto findById(Long id) {
        RestaurantTable table = restaurantTableRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The table with id "  + id + " could not be found"));

        return restaurantTableMapper.toDto(table);
    }

    @Transactional
    public RestaurantTableDto save(RestaurantTableRequestDto dto) {
        if(restaurantTableRepository.existsByName(dto.getName())) {
            throw new ResourceAlreadyExistsException("The table with name " + dto.getName() + " already exists");
        }
        
        Area area = areaRepository.findById(dto.getAreaId()).orElseThrow(() -> new ResourceNotFoundException("The area with id "  + dto.getAreaId() + " could not be found"));
        
        RestaurantTable table = restaurantTableMapper.toEntity(dto);
        table.setArea(area);
        restaurantTableRepository.save(table);

        return restaurantTableMapper.toDto(table);
    }

    @Transactional
    public RestaurantTableDto update(RestaurantTableRequestDto dto, Long id) {
        RestaurantTable table = restaurantTableRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The table with id "  + id + " could not be found"));

        if(restaurantTableRepository.existsByNameAndIdNot(dto.getName(), id)) {
            throw new ResourceAlreadyExistsException("The table with name " + dto.getName() + " already exists");
        }

        if(dto.getAreaId() != null) {
            Area area = areaRepository.findById(dto.getAreaId()).orElseThrow(() -> new ResourceNotFoundException("The area with id "  + dto.getAreaId() + " could not be found"));
            table.setArea(area);
        }

        if(dto.getName() != null) {
            table.setName(dto.getName());
        }

        if(dto.getCapacity() != null) {
            table.setCapacity(dto.getCapacity());
        }

        if(dto.getDescription() != null) {
            table.setDescription(dto.getDescription());
        }

        if(dto.getAvailable() != null) {
            table.setAvailable(dto.getAvailable());
        }

        restaurantTableRepository.save(table);

        return restaurantTableMapper.toDto(table);
    }

    @Transactional(readOnly = true)
    public Page<RestaurantTableShortInfoDto> findByAreaId(Long areaId, Integer page) {
        Area area = areaRepository.findById(areaId).orElseThrow(() -> new ResourceNotFoundException("The area with id "  + areaId + " could not be found"));

        long numElements = restaurantTableRepository.countByArea(area);
        page = CorrectPage.setCorrectPage(numElements, PaginationData.DEFAULT_PAGE_SIZE.getValue(), page);

        Page<RestaurantTable> tables = restaurantTableRepository.findByArea(area, PageRequest.of(page - 1, PaginationData.DEFAULT_PAGE_SIZE.getValue()));

        return tables.map(restaurantTableMapper::toShortInfoDto);
    }
}
