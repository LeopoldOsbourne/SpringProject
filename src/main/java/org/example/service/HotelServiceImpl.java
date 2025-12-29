package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.hotel.*;
import org.example.mapper.HotelMapper;
import org.example.model.Hotel;
import org.example.repository.HotelFilter;
import org.example.repository.HotelRepository;
import org.example.repository.HotelSpecification;
import org.springframework.data.domain.Page;
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
    public List<HotelResponseDto> getAll(HotelFilterDto hotelFilterDto) {

        HotelFilter hotelFilter;

        return hotelMapper.toHotelResponseList(hotelRepository.findAll(new HotelSpecification(hotelFilter)));
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

    /* @Override
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
    }*/


    /*public Page<HotelPageResponseDto<?>> findAllWithFilters(HotelFilterDto hotelFilterDto) {
        return null; //TODO
    }*/

    /*public Page<HotelPageResponseDto<?>> findAllWithFilters(HotelFilterDto hotelFilterDto) {

        Specification<Hotel> specification = HotelSpecification.filterByCriteria(hotelFilterDto);

        Page<Hotel> hotelPage = hotelRepository.findAll(specification, hotelFilterDto.getPageable());

        return hotelPage.map(this::convertToHotelPageResponseDto);

    }

    private HotelPageResponseDto<?> convertToHotelPageResponseDto(Hotel hotel) {
        return new HotelPageResponseDto<>();
    }*/


    @Override
    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("User not found with id " + id));
        hotelRepository.delete(hotel);
    }
}
