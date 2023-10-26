package com.codecool.scheduler.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
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
}
