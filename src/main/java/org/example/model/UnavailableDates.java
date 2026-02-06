package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "unavailable_dates")
@Data
@NoArgsConstructor
public class UnavailableDates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    Room room;

    @Column(nullable = false, name = "unavailable_date")
    LocalDate unavailableDate;

    @Enumerated(EnumType.STRING)
    Type type;

}
