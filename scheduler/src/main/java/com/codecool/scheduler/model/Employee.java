package com.codecool.scheduler.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Employee implements Comparable<Employee>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Request> currentRequests;
    private int hoursPerMonth;
    private int remainingHoursThisMonth;

    public void addWorkedHours(int workHours){
        remainingHoursThisMonth -= workHours;
    }

    public boolean isAvailable(LocalDate date){
       return currentRequests.stream().noneMatch(request -> request.getDate().equals(date));
    }

    @Override
    public int compareTo(Employee o) {
        return  o.currentRequests.size() - this.currentRequests.size();
    }

    @Override
    public String toString() {
        return "name:" + name;
    }
}
