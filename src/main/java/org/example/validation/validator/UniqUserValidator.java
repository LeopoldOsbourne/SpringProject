package org.example.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.example.dto.user.UserRequestDto;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.validation.annotation.NotExist;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqUserValidator implements ConstraintValidator<NotExist, UserRequestDto> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(UserRequestDto userRequestDto, ConstraintValidatorContext constraintValidatorContext) {
        User byEmail = userRepository.findByEmail(userRequestDto.getEmail());
        if (byEmail != null) {
            return false;
        }

        User byName = userRepository.findByName(userRequestDto.getName());

        return byName == null;
    }
}
