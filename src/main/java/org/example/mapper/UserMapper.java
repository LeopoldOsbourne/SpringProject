package org.example.mapper;

import org.example.dto.room.RoomRequestDto;
import org.example.dto.room.RoomResponseDto;
import org.example.dto.user.UserRequestDto;
import org.example.dto.user.UserResponseDto;
import org.example.model.Room;
import org.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toUser(UserRequestDto userDto);

    UserResponseDto toUserResponseDto(User user);

    List<UserResponseDto> toUserResponseList(List<User> users);
}
