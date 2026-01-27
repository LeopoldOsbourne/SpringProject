package org.example.repository;

import jakarta.persistence.criteria.*;
import org.example.model.Room;
import org.example.model.UnavailableDates;
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
        if(roomFilter.getMinPrice() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("minimum price"), roomFilter.getMinPrice()));
        }
        if(roomFilter.getMaxPrice() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("maximum price"), roomFilter.getMaxPrice()));
        }
        if(roomFilter.getMaxNumberOfGuests() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("maxNumberOfGuests"), roomFilter.getMaxNumberOfGuests()));
        }

        if (roomFilter.getCheckIn() != null && roomFilter.getCheckOut() != null) {
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<UnavailableDates> unavailableRoot = subquery.from(UnavailableDates.class);


            Predicate roomMatch = criteriaBuilder.equal(unavailableRoot.get("room"), root);
            Predicate overlaps = criteriaBuilder.and(
                    criteriaBuilder.lessThanOrEqualTo(
                            criteriaBuilder.literal(roomFilter.getCheckIn()),
                            unavailableRoot.get("unavailableDate")
                    ),
                    criteriaBuilder.lessThanOrEqualTo(
                            unavailableRoot.get("unavailableDate"),
                            criteriaBuilder.literal(roomFilter.getCheckOut())
                    )
            );

            subquery.select(criteriaBuilder.literal(1L))
                    .where(criteriaBuilder.and(roomMatch, overlaps));

            predicate = criteriaBuilder.and(predicate, criteriaBuilder.not(criteriaBuilder.exists(subquery)));
        }

        if(roomFilter.getMaxNumberOfGuests() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("hotel").get("id"), roomFilter.getHotelId()));
        }

        return predicate;
    }
}
