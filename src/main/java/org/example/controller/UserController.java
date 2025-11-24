package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.user.UserRequestDto;
import org.example.dto.user.UserResponseDto;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(UserRequestDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findByName(String name) {
        return ResponseEntity.ok(userService.findByName(name));
    }
}
