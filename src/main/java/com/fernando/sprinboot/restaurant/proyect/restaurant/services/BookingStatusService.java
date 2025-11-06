package com.fernando.sprinboot.restaurant.proyect.restaurant.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.sprinboot.restaurant.proyect.restaurant.constants.PaginationData;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.bookingStatus.BookingStatusDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.helpers.CorrectPage;
import com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.BookingStatusMapper;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.BookingStatus;
import com.fernando.sprinboot.restaurant.proyect.restaurant.repositories.BookingStatusRepository;

@Service
public class BookingStatusService {

    private final BookingStatusMapper bookingStatusMapper;
    private final BookingStatusRepository bookingStatusRepository;

    public BookingStatusService(BookingStatusRepository bookingStatusRepository, BookingStatusMapper bookingStatusMapper) {
        this.bookingStatusRepository = bookingStatusRepository;
        this.bookingStatusMapper = bookingStatusMapper;
    }

    @Transactional(readOnly = true)
    public Page<BookingStatusDto> findAll(Integer page) {
        long numElements = bookingStatusRepository.count();
        page = CorrectPage.setCorrectPage(numElements, PaginationData.DEFAULT_PAGE_SIZE.getValue(), page);
        Pageable pageable = PageRequest.of(page - 1, PaginationData.DEFAULT_PAGE_SIZE.getValue());

        Page<BookingStatus> statuses = bookingStatusRepository.findAll(pageable);
        return statuses.map(bookingStatusMapper::toDto);
    }

    public BookingStatusDto findById(Long id) {
        BookingStatus status = bookingStatusRepository.findById(id).orElse(null);
        return bookingStatusMapper.toDto(status);
    }
}
