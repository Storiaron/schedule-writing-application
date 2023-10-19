package com.codecool.scheduler.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;
    private int minEmployees;
    private int preferredEmployees;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return minEmployees == day.minEmployees && preferredEmployees == day.preferredEmployees && Objects.equals(id, day.id) && Objects.equals(date, day.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, minEmployees, preferredEmployees);
    }
}
