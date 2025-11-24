package org.example.dto.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.model.UserRole;

import java.util.Set;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto {

    UUID id;

    String name;

    String password;

    String email;

    Set<UserRole> roles;
}
