package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.hotel.*;
import org.example.mapper.HotelMapper;
import org.example.model.Hotel;
import org.example.repository.HotelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public List<HotelResponseDto> getAll() {
        return hotelMapper.toHotelResponseList(hotelRepository.findAll());
    }

    @Override
    public HotelResponseDto findById(Long id) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isEmpty()) {
            return null;
        }

        return hotelMapper.toHotelResponseDto(hotelOptional.get()) ;
    }

    @Override
    public HotelResponseDto create(HotelRequestDto hotelDto) {
        Hotel hotel = hotelMapper.toHotel(hotelDto);
        return hotelMapper.toHotelResponseDto(hotelRepository.save(hotel))  ;
    }

    public HotelResponseDto edit(HotelRequestDto hotelDto, Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Not found hotel"));
        return hotelMapper.toHotelResponseDto(hotelRepository.save(hotel));
    }

    @Override
    public HotelResponseWithRatingDto updateHotelRating(Long hotelId, int newMark) {
        if (newMark < 1 || newMark > 5) {
            throw new IllegalArgumentException("Оценка должна быть от 1 до 5.");
        }
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new NoSuchElementException("Отель не найден."));

        double totalRating = hotel.getRating() * hotel.getNumberOfRating();

        totalRating = totalRating - hotel.getRating() + newMark;

        int updatedNumberOfRating = hotel.getNumberOfRating() + 1;
        hotel.setNumberOfRating(updatedNumberOfRating);

        double newRating = totalRating / updatedNumberOfRating;
        hotel.setRating((long) (Math.round(newRating * 10) / 10.0));


        return hotelMapper.toHotelResponseWithRatingDto(hotelRepository.save(hotel));
    }

    @Override
    public Page<HotelPageResponseDto<?>> findAllWithFilters(Long id, String name, String adTitle, String city, String address, Double distanceToCenter, Double rating, Integer numberOfRating, java.awt.print.Pageable pageable) {
        return null; //TODO
    }

    public Page<Hotel> findAllWithFilters(
            Long id, String name, String adTitle, String city, String address,
            Double distanceToCenter, Double rating, Integer numberOfRating,
            Pageable pageable) {

        Specification<Hotel> specification = HotelSpecification.filterByCriteria(
                id, name, adTitle, city, address, distanceToCenter, rating, numberOfRating);

        return hotelRepository.findAll(specification, pageable);
    }

    @Override
    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("User not found with id " + id));
        hotelRepository.delete(hotel);
    }
}
