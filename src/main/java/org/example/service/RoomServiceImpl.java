package org.example.service;

import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.room.RoomRequestDto;
import org.example.dto.room.RoomResponseDto;
import org.example.dto.room.RoomSpecification;
import org.example.mapper.RoomMapper;
import org.example.model.Room;
import org.example.model.UnavailableDates;
import org.example.repository.RoomRepository;
import org.example.repository.UnavailableDatesRepository;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final UnavailableDatesRepository unavailableDatesRepository;

    @Override
    @Nullable
    public RoomResponseDto findById(Long id) {
        Optional<Room> optional = roomRepository.findById(id);
        Room room = optional.orElse(null);
        if (room == null) {
            return null;
        }

        return roomMapper.toRoomResponseDto(room);
    }

    @Override
    @Transactional
    public RoomResponseDto create(RoomRequestDto roomDto) {
        /*
        1. сохранить комнату
        2. создать список из UnavailableDates из roomDto.unavailableDates, в каждую из них положить созданный Room
        3. Сохранить список UnavailableDates через saveBatch
         */
        Room room = roomMapper.toRoom(roomDto);
        room = roomRepository.save(room);

        Room finalRoom = room;
        List<UnavailableDates> unavailableDates = roomDto.getUnavailableDates()
                .stream()
                .map(date -> {
                    UnavailableDates unavailableDate = new UnavailableDates();
                    unavailableDate.setRoom(finalRoom);
                    return unavailableDate;
                })
                .collect(Collectors.toList());

        unavailableDatesRepository.saveAll(unavailableDates);

        roomRepository.save(room);
        return roomMapper.toRoomResponseDto(room);
    }

    @Override
    public RoomResponseDto edit(RoomRequestDto roomDto, Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Not found room"));
        room.setName(roomDto.getName());
        room.setDescription(roomDto.getDescription());
        room.setPrice(roomDto.getPrice());
        room.setMaxNumberOfGuests(roomDto.getMaxNumberOfGuests());
        return roomMapper.toRoomResponseDto(roomRepository.save(room));
    }

    public Page findAllWithFilters(
            Long id, String title,
            Double minPrice, Double maxPrice,
            Integer guestCount,
            LocalDate checkInDate, LocalDate checkOutDate,
            Long hotelId,
            Pageable pageable) {

        Specification<Room> specification = RoomSpecification.filterByCriteria(
                id, title, minPrice, maxPrice, guestCount, checkInDate, checkOutDate, hotelId);

        return roomRepository.findAll(specification, pageable);
    }

    @Override
    public void delete(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("User not found with id " + id));
        roomRepository.delete(room);
    }
}
