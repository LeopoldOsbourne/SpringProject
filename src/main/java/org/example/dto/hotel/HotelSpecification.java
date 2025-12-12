package org.example.dto.hotel;

import jakarta.persistence.criteria.Predicate;
import org.example.model.Hotel;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class HotelSpecification {

    public static Specification<Hotel> filterByCriteria(HotelFilterDto hotelFilterDto) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (hotelFilterDto.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), hotelFilterDto.getId()));
            }
            if (hotelFilterDto.getName() != null && !hotelFilterDto.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + hotelFilterDto.getName().toLowerCase() + "%"));
            }
            if (hotelFilterDto.getAdTitle() != null && !hotelFilterDto.getAdTitle().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("adTitle")),
                        "%" + hotelFilterDto.getAdTitle().toLowerCase() + "%"));
            }
            if (hotelFilterDto.getCity() != null && !hotelFilterDto.getCity().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("city")),
                        "%" + hotelFilterDto.getCity().toLowerCase() + "%"));
            }
            if (hotelFilterDto.getAddress() != null && !hotelFilterDto.getAddress().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("address")),
                        "%" + hotelFilterDto.getAddress().toLowerCase() + "%"));
            }
            if (hotelFilterDto.getDistanceToCenter() != null) {
                predicates.add(criteriaBuilder.equal(root.get("distanceToCenter"),
                        hotelFilterDto.getDistanceToCenter()));
            }
            if (hotelFilterDto.getRating() != null) {
                predicates.add(criteriaBuilder.equal(root.get("rating"),
                        hotelFilterDto.getRating()));
            }
            if (hotelFilterDto.getNumberOfRating() != null) {
                predicates.add(criteriaBuilder.equal(root.get("numberOfRating"),
                        hotelFilterDto.getNumberOfRating()));
            }

            return predicates.isEmpty()
                    ? criteriaBuilder.conjunction()
                    : criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}

