package com.codecool.scheduler.model;


import lombok.Getter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Getter
public class Schedule extends HashMap{
    private UUID id;
    private Map<LocalDate, List<Employee>> schedule;

    public Schedule() {
        this.id = UUID.randomUUID();
        this.schedule = new HashMap<>();
    }
}
