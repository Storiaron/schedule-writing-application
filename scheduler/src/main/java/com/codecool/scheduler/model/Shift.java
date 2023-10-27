package com.codecool.scheduler.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    private int minEmployees;
    private int preferredEmployees;
    @ManyToOne
    @JsonBackReference
    private Day day;
    @ManyToMany(mappedBy = "scheduledShifts")
    @JsonManagedReference
    private List<Employee> scheduledEmployees;

    @Override
    public String toString() {
        return "Shift{" +
                "shiftStart=" + shiftStart +
                ", scheduledEmployees=" + scheduledEmployees +
                '}';
    }
}
