package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.booking.BookingRequestDto;
import org.example.dto.booking.BookingResponseDto;
import org.example.exception.RoomUnavailableException;
import org.example.mapper.BookingMapper;
import org.example.model.*;
import org.example.repository.BookingRepository;
import org.example.repository.RoomRepository;
import org.example.repository.UnavailableDatesRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UnavailableDatesRepository unavailableDatesRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

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
            throw new RoomUnavailableException("room " + bookingDto.getRoomId() + " is not available these dates "
            + bookingDto.getArrivalDate() + " - " + bookingDto.getDepartureDate());
        }

        //TODO тут делаем п.2
        LocalDate currentDate = bookingDto.getArrivalDate();

        Room room = roomRepository.findById(bookingDto.getRoomId())
                .orElseThrow(() -> new EntityNotFoundException("room not found" + bookingDto.getRoomId()));

        while(!currentDate.isAfter(bookingDto.getDepartureDate())) {
            UnavailableDates unavailableDates = new UnavailableDates();
            // у тебя есть id комнаты, надо найти по нему комнату и установить ее в UD

            unavailableDates.setRoom(room);
            unavailableDates.setUnavailableDate(currentDate);
            unavailableDates.setType(Type.BOOKING);

            unavailableDatesRepository.save(unavailableDates);
            currentDate = currentDate.plusDays(1);
        }



        Booking booking = bookingMapper.toBooking(bookingDto);
        booking.setRoom(room);
        User user = userRepository.findById(bookingDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("user not found" + bookingDto.getUserId()));
        booking.setUser(user);
        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toBookingResponseDto(savedBooking);
    }

    @Override
    public void deleteBooking(long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Booking is not found"));
        bookingRepository.delete(booking);
    }
}
