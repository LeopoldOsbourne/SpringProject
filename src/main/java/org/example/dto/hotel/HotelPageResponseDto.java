package org.example.dto.hotel;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelPageResponseDto<T> {

    long id;

    private List<T> content;

    private long totalElements;

    List<T> elements;

}
