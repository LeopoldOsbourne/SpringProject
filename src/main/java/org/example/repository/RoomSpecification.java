package org.example.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.model.Room;
import org.springframework.data.jpa.domain.Specification;

public class RoomSpecification implements Specification<Room> {
    private final RoomFilter roomFilter;

    public RoomSpecification(RoomFilter roomFilter) {
        this.roomFilter = roomFilter;
    }

    @Override
    public Predicate toPredicate(Root<Room> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if(roomFilter.getId() != null){
            predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), roomFilter.getId()));
        }
        if (roomFilter.getName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("name"), roomFilter.getName()));
        }
        if(roomFilter.getDescription() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("description"), roomFilter.getDescription()));
        }
        if(roomFilter.getNumber() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("number"), roomFilter.getNumber()));
        }
        if(roomFilter.getMinPrice() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("minimum price"), roomFilter.getMinPrice()));
        }
        if(roomFilter.getMaxPrice() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("maximum price"), roomFilter.getMaxPrice()));
        }
        if(roomFilter.getMaxNumberOfGuests() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("maxNumberOfGuests"), roomFilter.getMaxNumberOfGuests()));
        }

        return predicate;
    }
}
