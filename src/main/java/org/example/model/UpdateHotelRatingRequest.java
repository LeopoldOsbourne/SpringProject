package org.example.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateHotelRatingRequest {

    @Max(5)
    @Min(1)
    Integer mark;
    Long userId;
}
