package com.fernando.sprinboot.restaurant.proyect.restaurant.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.sprinboot.restaurant.proyect.restaurant.constants.PaginationData;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaFullInfoDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaRequestDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaShortInfoDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaTreeDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.exceptions.BussinessException;
import com.fernando.sprinboot.restaurant.proyect.restaurant.exceptions.ResourceAlreadyExistsException;
import com.fernando.sprinboot.restaurant.proyect.restaurant.exceptions.ResourceNotFoundException;
import com.fernando.sprinboot.restaurant.proyect.restaurant.helpers.CorrectPage;
import com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.AreaMapper;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.Area;
import com.fernando.sprinboot.restaurant.proyect.restaurant.repositories.AreaRepository;

@Service
public class AreaService  {

    private final AreaMapper areaMapper;
    private final AreaRepository areaRepository;

    public AreaService(
        AreaRepository areaRepository, 
        AreaMapper areaMapper
    ) {
        this.areaRepository = areaRepository;
        this.areaMapper = areaMapper;
    }

    @Transactional(readOnly = true)
    public AreaFullInfoDto findById(Long id) {
        Area area = findEntityById(id);

        return areaMapper.toDto(area);
    }

    @Transactional(readOnly = true)
    private Area findEntityById(Long id) {
        return areaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Area with id " + id + " not found"));
    }

    @Transactional
    public AreaFullInfoDto save(AreaRequestDto body) {
        // Verificar si el nombre ya existe
        if(areaRepository.existsByName(body.getName())) {
            throw new ResourceAlreadyExistsException("The area with name " + body.getName() +   " already exists");
        }

        Area parentArea = null;

        // Verificando si el area padre si exsite
        if (body.getParentAreaId() != null) {
            parentArea = findEntityById(body.getParentAreaId());
        }
        Area areaToSave = areaMapper.fromBodyToEntity(body);
        areaToSave.setParentArea(parentArea);
        areaRepository.save(areaToSave);

        return areaMapper.toDto(areaToSave);
    }

    @Transactional(readOnly = true)
    public Page<AreaFullInfoDto> findAllFullInfo(Integer page, String name, Boolean available) {
        long numElements = areaRepository.countByNameContainingIgnoringCaseAndAvailable(name, available);
        page = CorrectPage.setCorrectPage(numElements, PaginationData.DEFAULT_PAGE_SIZE.getValue(), page);

        Page<Area> areas = areaRepository.findByNameContainingIgnoringCaseAndAvailable(name, available, PageRequest.of(page - 1, PaginationData.DEFAULT_PAGE_SIZE.getValue()));

        return areas.map(areaMapper::toDto);

    }

    @Transactional(readOnly = true)
    public Page<AreaShortInfoDto> findAllShortInfo(Integer page, String name, Boolean available) {
        long numElements = areaRepository.countByNameContainingIgnoringCaseAndAvailable(name, available);
        page = CorrectPage.setCorrectPage(numElements, PaginationData.DEFAULT_PAGE_SIZE.getValue(), page);

        Page<Area> areas = areaRepository.findByNameContainingIgnoringCaseAndAvailable(name, available, PageRequest.of(page - 1, PaginationData.DEFAULT_PAGE_SIZE.getValue()));

        return areas.map(areaMapper::toShortInfoDto);
    }

    @Transactional
    public AreaFullInfoDto update(AreaRequestDto body, Long id) {
        //Verificar si el area si existe
        Area area = areaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Area with id " + id + " not found"));

        if(body.getName() != null) {

            AreaShortInfoDto existingArea =  areaRepository.findShortInfoByName(body.getName()).orElse(null);
    
            //Verificar que el nombre no sea igual a otra area
            if(existingArea != null && !existingArea.getId().equals(id) ) {
                throw new ResourceAlreadyExistsException("Area with name " + existingArea.getName() + " already exists");
            }

            area.setName(body.getName());
        }

        
        Area parentArea = null;

        if (body.getParentAreaId() != null) {
            //Verificar que el padre exista
            parentArea = findEntityById(body.getParentAreaId());

            Long grandParentId = parentArea.getParentArea() != null ? parentArea.getParentArea().getId() : null;
            AreaShortInfoDto current = new AreaShortInfoDto(parentArea.getId(), parentArea.getName(), grandParentId);

            //Verificar que el padre no sea un hijo del area
            while (current != null) {
                if (current.getId().equals(id)) {
                    throw new BussinessException("Cyclic parent relationship detected");
                }
                current = areaRepository.findShortInfoById(current.getParentAreaId()).orElse(null);
            }

            area.setParentArea(parentArea);
        }

        if(body.getDescription() != null) {
            area.setDescription(body.getDescription());
        }

        if(body.getAvailable() != null) {
            area.setAvailable(body.getAvailable());
        }

        areaRepository.save(area);
        return areaMapper.toDto(area);
    }

    @Transactional(readOnly = true)
    public Page<AreaFullInfoDto> getChildrenAreas(Long id, Integer page) {
        Area area = findEntityById(id);
        long numElements = areaRepository.countByParentArea(area);
        page = CorrectPage.setCorrectPage(numElements, PaginationData.DEFAULT_PAGE_SIZE.getValue(), page);

        Page<Area> childrenAreas = areaRepository.findByParentArea(area, PageRequest.of(page - 1, PaginationData.DEFAULT_PAGE_SIZE.getValue()));

        return childrenAreas.map(areaMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<AreaShortInfoDto> getChildrenAreaShortInfoPage(Long id, Integer page) {
        Area area = findEntityById(id);

        long numElements = areaRepository.countByParentArea(area);
        page = CorrectPage.setCorrectPage(numElements, PaginationData.DEFAULT_PAGE_SIZE.getValue(), page);

        Page<Area> childrenAreas = areaRepository.findByParentArea(area, PageRequest.of(page - 1, PaginationData.DEFAULT_PAGE_SIZE.getValue()));

        return childrenAreas.map(areaMapper::toShortInfoDto);
    }

    @Transactional(readOnly = true)
    public Page<AreaTreeDto> getAreaTree(Integer page) {
        long numElements = areaRepository.countByParentAreaIsNull();
        page = CorrectPage.setCorrectPage(numElements, PaginationData.DEFAULT_PAGE_SIZE.getValue(), page);

        Page<Area> rootAreas = areaRepository.findByParentAreaIsNull(PageRequest.of(page - 1, PaginationData.DEFAULT_PAGE_SIZE.getValue()));

        return rootAreas.map(areaMapper::setChildrenAreas);
    }

    @Transactional(readOnly = true)
    public AreaTreeDto getAreaTreeById(Long id) {
        Area area = findEntityById(id);

        return areaMapper.setChildrenAreas(area);
    }

}
