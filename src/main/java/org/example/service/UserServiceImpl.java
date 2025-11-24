package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.user.UserRequestDto;
import org.example.dto.user.UserResponseDto;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto create(UserRequestDto userDto) {
        User user = userMapper.toUser(userDto);
        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto findByName(String name) {
        return userMapper.toUserResponseDto(userRepository.findByName(name));
    }
}
