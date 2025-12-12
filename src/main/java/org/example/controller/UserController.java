package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.user.UserRequestDto;
import org.example.dto.user.UserResponseDto;
import org.example.service.UserService;
import org.example.validation.annotation.NotExist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponseDto create(@RequestBody @NotExist @Valid UserRequestDto userDto) {
        return userService.create(userDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findByName(String name) {
        return ResponseEntity.ok(userService.findByName(name));
    }
}
