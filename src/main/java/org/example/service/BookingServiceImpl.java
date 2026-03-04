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
import org.example.statistics.producer.StatisticsEventProducer;
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
    private final StatisticsEventProducer statisticsEventProducer;

    @Override
    public List<BookingResponseDto> showAllBookings() {
        return bookingMapper.toBookingResponseList(bookingRepository.findAll());
    }

    @Override
    public BookingResponseDto createBooking(BookingRequestDto bookingDto) {
        List<UnavailableDates> dates = unavailableDatesRepository.findByRoomIdAndUnavailableDateBetween(bookingDto.getRoomId(), bookingDto.getArrivalDate(), bookingDto.getDepartureDate());
        if(!dates.isEmpty()) {
            throw new RoomUnavailableException("room " + bookingDto.getRoomId() + " is not available these dates "
            + bookingDto.getArrivalDate() + " - " + bookingDto.getDepartureDate());
        }

        LocalDate currentDate = bookingDto.getArrivalDate();

        Room room = roomRepository.findById(bookingDto.getRoomId())
                .orElseThrow(() -> new EntityNotFoundException("room not found" + bookingDto.getRoomId()));

        while(!currentDate.isAfter(bookingDto.getDepartureDate())) {
            UnavailableDates unavailableDates = new UnavailableDates();

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
        statisticsEventProducer.sendRoomBookingEvent(user.getId(), savedBooking.getArrivalDate(), savedBooking.getDepartureDate());
        return bookingMapper.toBookingResponseDto(savedBooking);
    }

    @Override
    public void deleteBooking(long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Booking is not found"));
        bookingRepository.delete(booking);
    }
}
