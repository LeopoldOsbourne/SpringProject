package org.example.service;

import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.RoomResponse;
import org.example.dto.room.RoomRequestDto;
import org.example.dto.room.RoomResponseDto;
import org.example.mapper.RoomMapper;
import org.example.model.Hotel;
import org.example.model.Room;
import org.example.model.Type;
import org.example.model.UnavailableDates;
import org.example.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final UnavailableDatesRepository unavailableDatesRepository;
    private final HotelRepository hotelRepository;

    @Override
    public RoomResponse getAll(RoomFilter roomFilter) {
        Page<Room> rooms = roomRepository.findAll(new RoomSpecification(roomFilter), PageRequest.of(roomFilter.getPageNumber(), roomFilter.getPageSize()));
        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setRooms(rooms.getContent().stream().map(roomMapper::toRoomResponseDto).collect(Collectors.toList()));
        roomResponse.setPageNumber(rooms.getNumber());
        roomResponse.setPageSize(rooms.getSize());
        roomResponse.setPageElements(rooms.getNumber());
        return roomResponse;
    }

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
        Room room = roomMapper.toRoom(roomDto);
        Hotel hotel = hotelRepository.findById(roomDto.getHotelId())
                .orElseThrow(()-> new EntityNotFoundException("Hotel not found"));
        room.setHotel(hotel);
        room = roomRepository.save(room);

        Room finalRoom = room;
        List<UnavailableDates> unavailableDates = roomDto.getUnavailableDates()
                .stream()
                .map(date -> {
                    UnavailableDates unavailableDate = new UnavailableDates();
                    unavailableDate.setRoom(finalRoom);
                    unavailableDate.setType(Type.UNAVAILABLE);
                    unavailableDate.setUnavailableDate(date);
                    return unavailableDate;
                })
                .collect(Collectors.toList());

        unavailableDatesRepository.saveAll(unavailableDates);

        List<UnavailableDates> savedDates = unavailableDatesRepository.saveAll(unavailableDates);

        room.setUnavailableDates(savedDates);
        roomRepository.save(room);
        return roomMapper.toRoomResponseDto(room);
    }

    @Override
    public RoomResponseDto edit(RoomRequestDto roomDto, Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Not found room"));
        room.setName(roomDto.getName());
        room.setDescription(roomDto.getDescription());
        room.setNumber(roomDto.getNumber());
        room.setPrice(roomDto.getPrice());
        room.setMaxNumberOfGuests(roomDto.getMaxNumberOfGuests());
        return roomMapper.toRoomResponseDto(roomRepository.save(room));
    }

    @Override
    public void delete(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("User not found with id " + id));
        roomRepository.delete(room);
    }
}
