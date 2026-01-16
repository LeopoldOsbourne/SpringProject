package org.example.mapper;

import org.example.dto.room.RoomRequestDto;
import org.example.dto.room.RoomResponseDto;
import org.example.model.Room;
import org.example.model.UnavailableDates;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomMapper {

    @Mapping(target = "id", ignore = true)
    Room toRoom(RoomRequestDto roomDto);

    @Mapping(source = "hotel.id", target = "id")
    RoomResponseDto toRoomResponseDto(Room room);

    List<RoomResponseDto> toRoomResponseList(List<Room> rooms);

}
