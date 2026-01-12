package org.example.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.model.Hotel;
import org.springframework.data.jpa.domain.Specification;

public class HotelSpecification implements Specification<Hotel> {
    private final HotelFilter  hotelFilter;

    public HotelSpecification(HotelFilter hotelFilter) {
        this.hotelFilter = hotelFilter;
    }

    @Override
    public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (hotelFilter.getId() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), hotelFilter.getId()));
        }
        if(hotelFilter.getName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), hotelFilter.getName()));
        }
        if(hotelFilter.getTitle() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("title"), hotelFilter.getTitle()));
        }
        if(hotelFilter.getCity() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("city"), hotelFilter.getCity()));
        }
        if(hotelFilter.getAddress() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("address"), hotelFilter.getAddress()));
        }
        if(hotelFilter.getDistanceToCenter() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("distanceToCenter"), hotelFilter.getDistanceToCenter()));
        }
        if(hotelFilter.getRating() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("rating"), hotelFilter.getRating()));
        }
        if(hotelFilter.getNumberOfRating() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("numberOfRating"), hotelFilter.getNumberOfRating()));
        }

        return predicate;
    }
}
