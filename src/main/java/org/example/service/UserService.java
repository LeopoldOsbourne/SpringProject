package org.example.service;

import org.example.dto.user.UserRequestDto;
import org.example.dto.user.UserResponseDto;

public interface UserService {

    UserResponseDto create(UserRequestDto userDto);

    UserResponseDto findByName(String name);

    UserResponseDto edit(UserRequestDto userDto, long id);

    void delete(long id);
}
