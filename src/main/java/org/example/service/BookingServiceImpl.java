package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.booking.BookingRequestDto;
import org.example.dto.booking.BookingResponseDto;
import org.example.mapper.BookingMapper;
import org.example.model.Booking;
import org.example.model.UnavailableDates;
import org.example.repository.BookingRepository;
import org.example.repository.UnavailableDatesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UnavailableDatesRepository unavailableDatesRepository;

    @Override
    public List<BookingResponseDto> showAllBookings() {
        return bookingMapper.toBookingResponseList(bookingRepository.findAll());
    }

    @Override
    public BookingResponseDto createBooking(BookingRequestDto bookingDto) {
        /*
        1. проверить свободна ли комната на указанные даты
        2. если да, то добавить в таблицу unavailable_dates новые занятые даты с типом booking, преобразовав диапазон дат в отдельные даты
         */
        List<UnavailableDates> dates = unavailableDatesRepository.findByRoomIdAndUnavailableDateBetween(bookingDto.getRoomId(), bookingDto.getArrivalDate(), bookingDto.getDepartureDate());
        if(!dates.isEmpty()) {
            //TODO надо как-то показать что бронирование невозможно
        }

        //TODO тут делаем п.2
        Booking booking = bookingMapper.toBooking(bookingDto);
        return bookingMapper.toBookingResponseDto(bookingRepository.save(booking));
    }

    @Override
    public void deleteBooking(long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Booking is not found"));
        bookingRepository.delete(booking);
    }
}
