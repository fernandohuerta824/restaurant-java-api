package com.fernando.sprinboot.restaurant.proyect.restaurant.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.sprinboot.restaurant.proyect.restaurant.constants.PaginationData;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.extraService.ExtraServiceDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.extraService.ExtraServiceRequestDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.exceptions.ResourceAlreadyExistsException;
import com.fernando.sprinboot.restaurant.proyect.restaurant.exceptions.ResourceNotFoundException;
import com.fernando.sprinboot.restaurant.proyect.restaurant.helpers.CorrectPage;
import com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.ExtraServicesMapper;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.ExtraService;
import com.fernando.sprinboot.restaurant.proyect.restaurant.repositories.ExtraSevicesRepository;

@Service
public class ExtraServiceService  {
    private final ExtraServicesMapper extraServicesMapper;
    private final ExtraSevicesRepository extraSevicesRepository;

    public ExtraServiceService(ExtraSevicesRepository extraSevicesRepository, ExtraServicesMapper extraServicesMapper) {
        this.extraSevicesRepository = extraSevicesRepository;
        this.extraServicesMapper = extraServicesMapper;
    }

    public Page<ExtraServiceDto> findAll(Integer page) {
        long numElements = extraSevicesRepository.count();
        page = CorrectPage.setCorrectPage(numElements, PaginationData.DEFAULT_PAGE_SIZE.getValue(), page);
        System.out.println(page);
        Pageable pageable = PageRequest.of(page - 1, PaginationData.DEFAULT_PAGE_SIZE.getValue());

        Page<ExtraService> services = extraSevicesRepository.findAll(pageable);
        return services.map(extraServicesMapper::toDto);
    }
    
    @Transactional(readOnly = true)
    public ExtraServiceDto findById(Long id) {
        ExtraService service = extraSevicesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The service with ID " + id + " cannot be found"));
        return extraServicesMapper.toDto(service);
    }

    @Transactional
    public ExtraServiceDto save(ExtraServiceRequestDto body) {
        if (extraSevicesRepository.existsByName(body.getName())) {
            throw new ResourceAlreadyExistsException("The service with the name" + body.getName() + " already exists");
        }

        ExtraService extraService = extraServicesMapper.toEntity(body);
        ExtraService savedExtraService = extraSevicesRepository.save(extraService);
        return extraServicesMapper.toDto(savedExtraService);
    }

    @Transactional
    public ExtraServiceDto update(Long id, ExtraServiceRequestDto body) {
        ExtraService existingExtraService = extraSevicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The service cannot be found"));

        if(body.getName() != null ) {
            if(extraSevicesRepository.existsByNameAndIdNot(body.getName(), id)) {
                throw new ResourceAlreadyExistsException("The service with the name " + body.getName() + " already exists");
            }
        }

        extraServicesMapper.updateProduct(body, existingExtraService);
        return extraServicesMapper.toDto(existingExtraService);
    }
}
