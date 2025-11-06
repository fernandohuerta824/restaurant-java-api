package com.fernando.sprinboot.restaurant.proyect.restaurant.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.sprinboot.restaurant.proyect.restaurant.exceptions.ResourceNotFoundException;

import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.booking.BookingDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.booking.BookingRequestDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.booking.BookingServiceRequestDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.exceptions.BussinessException;
import com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.BookingMapper;
import com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.BookingServiceMapper;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.Booking;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.BookingExtraService;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.BookingStatus;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.ExtraService;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.RestaurantTable;
import com.fernando.sprinboot.restaurant.proyect.restaurant.repositories.BookingRepository;
import com.fernando.sprinboot.restaurant.proyect.restaurant.repositories.BookingServiceRepository;
import com.fernando.sprinboot.restaurant.proyect.restaurant.repositories.BookingStatusRepository;
import com.fernando.sprinboot.restaurant.proyect.restaurant.repositories.ExtraSevicesRepository;
import com.fernando.sprinboot.restaurant.proyect.restaurant.repositories.RestaurantTableRepository;

@Service
public class BookingService {
    
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final BookingStatusRepository bookingStatusRepository;
    private final RestaurantTableRepository restaurantTableRepository;
    private final BookingServiceRepository bookingServiceRepository;
    private final ExtraSevicesRepository extraServicesRepository;
    private final BookingServiceMapper bookingServiceMapper;

    public BookingService(
        BookingRepository bookingRepository, 
        BookingMapper bookingMapper,
        BookingStatusRepository bookingStatusRepository,
        RestaurantTableRepository restaurantTableRepository,
        BookingServiceRepository bookingServiceRepository,
        ExtraSevicesRepository extraServicesRepository,
        BookingServiceMapper bookingServiceMapper
    ) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.bookingStatusRepository = bookingStatusRepository;
        this.restaurantTableRepository = restaurantTableRepository;
        this.bookingServiceRepository = bookingServiceRepository;
        this.extraServicesRepository = extraServicesRepository;
        this.bookingServiceMapper = bookingServiceMapper;
    }

    // Validar que la hora de inicio sea antes de la hora de fin
    public void validateBookingTime(
        Booking booking,
        LocalTime startTime,
        LocalTime endTime
    ) {
        if(startTime.isAfter(endTime)) {
            throw new BussinessException("The start time cannot be after or equal to the end time");
        }

        booking.setEndTime(endTime);
        booking.setStartTime(startTime);
    }

    // Validar que la fecha de la reserva no sea en el pasado
    public void validateBookingDate(
        Booking booking,
        LocalDateTime dateTime
    ) {
        if(dateTime.isBefore(LocalDateTime.now())) {
            throw new BussinessException("You cannot create a booking in the past");
        }

        booking.setDate(dateTime.toLocalDate());
    }

    // Agregar mesas a la reserva y validar su disponibilidad
    // Verifica que la mesa exista
    // Verificar que la mesa esté disponible en general
    // Verificar que la mesa no esté reservada en el mismo horario
    public void addTablesToBooking(Booking booking, Set<Long> tableIds) {
        tableIds.forEach(t -> {
            RestaurantTable table = restaurantTableRepository.findById(t)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid table ID: " + t));

            if(!table.getAvailable() || !table.getArea().getAvailable()) {
                throw new BussinessException("Table with ID " + t + " is not available");
            }

            if(bookingRepository.isTableNotAvailable(
                table.getId(),
                booking.getDate(),
                booking.getStartTime(),
                booking.getEndTime()
            )) {
                throw new BussinessException("Table with ID " + t + " is not available");
            }
                
            booking.addTable(table);
        });
    }

    // Agregar servicios adicionales a la reserva
    // Verifica que el servicio exista
    // Asigna el precio y la cantidad del servicio
    public void addServicesToBooking(Booking booking, Set<BookingServiceRequestDto> services) {
        services.forEach(s -> {
            ExtraService service = extraServicesRepository.findById(s.getId())
                .orElseThrow(() -> new ResourceNotFoundException("The service with ID " + s.getId() + " cannot be found"));

            BookingExtraService bookingService = new BookingExtraService();
            bookingService.setBooking(booking);

            BigDecimal price = s.getPrice() != null ? s.getPrice() : service.getPrice();
            bookingService.setPrice(price);

            Integer quantity = s.getQuantity() != null ? s.getQuantity() : 1;
            bookingService.setQuantity(quantity);
            bookingService.setExtraService(service);

            bookingServiceRepository.save(bookingService);
            booking.addExtraService(bookingService);
            
        });
    }

    @Transactional(readOnly = true)
    public List<BookingDto> findAll(
        LocalDate minDate,
        LocalDate maxDate,
        LocalTime startTime,
        Long statusId
    ){

        BookingStatus status =  bookingStatusRepository.findById(statusId == null ? 2L : statusId)
            .orElseThrow(() -> new ResourceNotFoundException("Invalid status ID"));

        List<Booking> bookings = bookingRepository.findBooking(minDate, maxDate, startTime, status);

        return bookingMapper.toListDto(bookings);
    }



    @Transactional(readOnly = true)
    public BookingDto findById(Long id) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + id));
        return bookingMapper.toDto(booking);
    }

    @Transactional
    public BookingDto save(BookingRequestDto dto) {
        Booking booking = bookingMapper.fromBodyToEntity(dto);

        validateBookingDate(booking, LocalDateTime.of(booking.getDate(), booking.getStartTime()));
        validateBookingTime(booking, booking.getStartTime(), booking.getEndTime());

        BookingStatus status = bookingStatusRepository.findById(2L)
            .orElseThrow(() -> new ResourceNotFoundException("Invalid status ID"));

        booking.setStatus(status);

        addTablesToBooking(booking, dto.getTableIds());
        addServicesToBooking(booking, dto.getServices());

        bookingRepository.save(booking);
        return bookingMapper.toDto(booking);
    }

    @Transactional
    public BookingDto update(Long id, BookingRequestDto dto) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + id));


        LocalDate date = dto.getDate() != null ? dto.getDate() : booking.getDate();
        LocalTime startTime = dto.getStartTime() != null ? dto.getStartTime() : booking.getStartTime();
        LocalTime endTime = dto.getEndTime() != null ? dto.getEndTime() : booking.getEndTime();
        validateBookingDate(booking, LocalDateTime.of(date, startTime));
        validateBookingTime(booking, startTime, endTime);

        if(dto.getCustomerName() != null) {
            booking.setCustomerName(dto.getCustomerName());
        }

        Set<Long> originalTableIds = booking.getTables().stream()
            .map(RestaurantTable::getId)
            .collect(java.util.stream.Collectors.toSet());

        Set<Long> addedTableIds = new HashSet<>(dto.getTableIds());
        addedTableIds.removeAll(originalTableIds);

        Set<Long> removedTableIds = new HashSet<>(originalTableIds);
        removedTableIds.removeAll(dto.getTableIds());

        addTablesToBooking(booking, addedTableIds);
        
        System.out.println("Removed Tables: " + removedTableIds.size());

        removedTableIds.forEach(t -> {
            RestaurantTable table = restaurantTableRepository.findById(t)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid table ID: " + t));
            booking.removeTable(table);
        });


        Set<BookingServiceRequestDto> originalServices = booking.getExtraServices().stream()
            .map(bookingServiceMapper::toRequestDto)
            .collect(Collectors.toSet());

        Set<BookingServiceRequestDto> addedServices = new HashSet<>(dto.getServices()); 
        addedServices.removeAll(originalServices);

        Set<BookingServiceRequestDto> removedServices = new HashSet<>(originalServices);
        removedServices.removeAll(dto.getServices());

        addServicesToBooking(booking, addedServices);

        removedServices.forEach(s -> {
            BookingExtraService bookingService = bookingServiceRepository.findById(s.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Invalid service ID: " + s.getId()));
            booking.removeExtraService(bookingService);
            bookingServiceRepository.delete(bookingService);
        });

        bookingRepository.save(booking);
        return bookingMapper.toDto(booking);
    }
}
