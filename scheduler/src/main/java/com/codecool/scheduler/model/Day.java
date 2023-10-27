package com.codecool.scheduler.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
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
    @OneToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Shift> shifts;
    @ManyToOne
    @JsonBackReference
    private Schedule schedule;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", date=" + date +
                ", shifts=" + shifts +
                ", schedule=" + schedule +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return Objects.equals(date, day.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

}
