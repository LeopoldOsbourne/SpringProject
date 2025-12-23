package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.user.UserRequestDto;
import org.example.dto.user.UserResponseDto;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.model.UserRole;
import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto create(UserRequestDto userDto) {
        User user = userMapper.toUser(userDto);

        UserRole role = roleRepository.findByName("user");
        user.setRoles(Set.of(role));

        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto findByName(String name) {
        return userMapper.toUserResponseDto(userRepository.findByName(name));
    }

    @Override
    public UserResponseDto edit(UserRequestDto userDto, long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Not found user"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User updatedUser = userRepository.save(user);
        return userMapper.toUserResponseDto(updatedUser);
    }

    @Override
    public void delete(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("User not found with id " + id));
        userRepository.delete(user);
    }
}
