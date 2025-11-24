package org.example.dto.hotel;

import jakarta.persistence.criteria.Predicate;
import org.example.model.Hotel;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class HotelSpecification {

    public static Specification<Hotel> filterByCriteria(
            Long id, String name, String adTitle, String city, String address,
            Double distanceToCenter, Double rating, Integer numberOfRating) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), id));
            }
            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (adTitle != null && !adTitle.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("adTitle")), "%" + adTitle.toLowerCase() + "%"));
            }
            if (city != null && !city.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("city")), "%" + city.toLowerCase() + "%"));
            }
            if (address != null && !address.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + address.toLowerCase() + "%"));
            }
            if (distanceToCenter != null) {
                predicates.add(criteriaBuilder.equal(root.get("distanceToCenter"), distanceToCenter));
            }
            if (rating != null) {
                predicates.add(criteriaBuilder.equal(root.get("rating"), rating));
            }
            if (numberOfRating != null) {
                predicates.add(criteriaBuilder.equal(root.get("numberOfRating"), numberOfRating));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

