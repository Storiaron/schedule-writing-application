package com.codecool.scheduler.model;


import lombok.Getter;

import java.time.LocalDate;
import java.util.*;

@Getter
public class Schedule {
    private UUID id;
    private Map<LocalDate, List<Employee>> schedule;

    public Schedule() {
        this.id = UUID.randomUUID();
        this.schedule = new HashMap<>();
    }
    public void put(LocalDate date, List<Employee> employees){
        schedule.put(date, employees);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "schedule=" + schedule +
                '}';
    }
}
