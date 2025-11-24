package org.example.dto.room;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.example.model.Booking;
import org.example.model.Room;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomSpecification {

    public static Specification<Room> filterByCriteria(
            Long roomId, String title,
            Double minPrice, Double maxPrice,
            Integer guestCount,
            LocalDate checkInDate, LocalDate checkOutDate,
            Long hotelId) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (roomId != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), roomId));
            }
            if (title != null && !title.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }
            if (minPrice != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }
            if (guestCount != null) {
                predicates.add(criteriaBuilder.equal(root.get("guestCount"), guestCount));
            }
            if (hotelId != null) {
                predicates.add(criteriaBuilder.equal(root.get("hotel").get("id"), hotelId));
            }

            // Фильтрация по датам заезда и выезда: учитываем наличие обеих дат
            if (checkInDate != null && checkOutDate != null) {
                // Подзапрос для проверки наличия бронирований, пересекающихся с указанным диапазоном
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<Booking> bookingRoot = subquery.from(Booking.class);
                subquery.select(bookingRoot.get("room").get("id"))
                        .where(
                                criteriaBuilder.equal(bookingRoot.get("room").get("id"), root.get("id")),
                                // Проверка пересечения дат бронирования с указанным интервалом
                                criteriaBuilder.lessThanOrEqualTo(bookingRoot.get("checkInDate"), checkOutDate),
                                criteriaBuilder.greaterThanOrEqualTo(bookingRoot.get("checkOutDate"), checkInDate)
                        );

                // Исключаем комнаты, у которых есть бронирования, пересекающиеся с указанным диапазоном
                predicates.add(criteriaBuilder.not(criteriaBuilder.exists(subquery)));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
