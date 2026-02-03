package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.HotelResponse;
import org.example.dto.hotel.*;
import org.example.mapper.HotelMapper;
import org.example.model.Hotel;
import org.example.model.HotelMark;
import org.example.model.User;
import org.example.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final HotelMarkRepository  hotelMarkRepository;
    private final UserRepository userRepository;

    @Override
    public HotelResponse getAll(HotelFilter hotelFilter) {
        Page<Hotel> hotels = hotelRepository.findAll(new HotelSpecification(hotelFilter), PageRequest.of(hotelFilter.getPageNumber(), hotelFilter.getPageSize()));
        HotelResponse hotelResponse = new HotelResponse();
        hotelResponse.setHotels(hotels.toList().stream().map(hotelMapper::toHotelResponseDto).toList());
        hotelResponse.setPageNumber(hotels.getNumber());
        hotelResponse.setPageSize(hotels.getSize());
        hotelResponse.setPageElements(hotels.getNumberOfElements());
        return hotelResponse;
    }

    @Override
    public HotelResponseDto findById(Long id) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);

        return hotelOptional
                .map(hotelMapper::toHotelResponseDto)
                .orElse(null);
    }

    @Override
    public HotelResponseDto create(HotelRequestDto hotelDto) {
        Hotel hotel = hotelMapper.toHotel(hotelDto);
        return hotelMapper.toHotelResponseDto(hotelRepository.save(hotel));
    }

    public HotelResponseDto edit(HotelRequestDto hotelDto, Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Not found hotel"));
        hotel.setName(hotelDto.getName());
        hotel.setAddress(hotelDto.getAddress());
        hotel.setCity(hotelDto.getCity());

        Hotel updatedHotel = hotelRepository.save(hotel);

        return hotelMapper.toHotelResponseDto(updatedHotel);
    }

    @Override
    public HotelResponseWithRatingDto updateHotelRating(Long hotelId, int newMark, Long userId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new NoSuchElementException("Hotel is not found."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User is not found."));

        HotelMark hotelMark = new HotelMark();
        hotelMark.setMark(newMark);
        hotelMark.setHotel(hotel);
        hotelMark.setUser(user);

        /*
        как найти сумму оценок и их количество???
        1. Получить список всех оценок из БД
        2. Найти сумму оценок из списка
        3. поделить полученную сумму на количество элементов списка
        4. записать рейтинг в ответ
         */
        hotelMarkRepository.save(hotelMark);

        HotelResponseWithRatingDto hotelResponseWithRatingDto = new HotelResponseWithRatingDto();
        hotelResponseWithRatingDto.setName(hotel.getName());
        hotelResponseWithRatingDto.setAddress(hotel.getAddress());
        hotelResponseWithRatingDto.setCity(hotel.getCity());
        hotelResponseWithRatingDto.setDistanceFromCityCenter(hotel.getDistanceFromCityCenter());
        hotelResponseWithRatingDto.setTitle(hotel.getTitle());

        hotel.setRating((long) (Math.round(newMark * 10.0) / 10.0));


        return
    }

    private int getRating(HotelResponseWithRatingDto hotel) {
        hotel.getRating();
    }

    @Override
    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("User not found with id " + id));
        hotelRepository.delete(hotel);
    }
}
