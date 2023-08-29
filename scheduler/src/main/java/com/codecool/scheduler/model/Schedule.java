package com.codecool.scheduler.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "schedule")
public class Schedule {
    @Id
    private LocalDate date;
    @Transient
    private List<Employee> employees;

    public Schedule createSchedule(){
        return null;
    }
}
