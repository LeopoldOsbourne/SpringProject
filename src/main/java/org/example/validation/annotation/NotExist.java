package org.example.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validation.validator.UniqUserValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.UniqueElementsValidator;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqUserValidator.class)
@Documented
public @interface NotExist {
    String message() default "User already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
