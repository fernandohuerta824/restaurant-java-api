package com.fernando.sprinboot.restaurant.proyect.restaurant.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.sprinboot.restaurant.proyect.restaurant.constants.PaginationData;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.paymentMethod.PaymentMethodDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.helpers.CorrectPage;
import com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.PaymentMethodMapper;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.PaymentMethod;
import com.fernando.sprinboot.restaurant.proyect.restaurant.repositories.PaymentMethodRepository;

@Service
public class PaymentMethodService {

    private final PaymentMethodMapper paymentMethodMapper;
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository, PaymentMethodMapper paymentMethodMapper) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentMethodMapper = paymentMethodMapper;
    }

    @Transactional(readOnly = true)
    public Page<PaymentMethodDto> findAll(Integer page) {
        long numElements = paymentMethodRepository.count();
        page = CorrectPage.setCorrectPage(numElements, PaginationData.DEFAULT_PAGE_SIZE.getValue(), page);
        Pageable pageable = PageRequest.of(page - 1, PaginationData.DEFAULT_PAGE_SIZE.getValue());

        Page<PaymentMethod> methods = paymentMethodRepository.findAll(pageable);
        return methods.map(paymentMethodMapper::toDto);
    }

    @Transactional(readOnly = true)
    public PaymentMethodDto findById(Long id) {
        PaymentMethod method = paymentMethodRepository.findById(id).orElseThrow();
        return paymentMethodMapper.toDto(method);
    }
    
}
