package com.codecool.scheduler.model;


import lombok.Getter;

import java.time.LocalDate;
import java.util.*;

@Getter
public class Schedule {
    private UUID id;
    private Map<Day, List<Employee>> schedule;

    public Schedule() {
        this.id = UUID.randomUUID();
        this.schedule = new HashMap<>();
    }
    public void put(Day date, List<Employee> employees){
        schedule.put(date, employees);
    }

    public List<Day> getWorkDays(Employee employee){
        List<Day> workdays = new ArrayList<>();
        schedule.forEach((date, employees) -> {
            if(employees.contains(employee)) {
            workdays.add(date);
            }
        });
        return workdays;
    }
    public int getContinuousWorkDays(Employee employee, LocalDate date){
        int counter = 1;
        for(int i = 1; i < schedule.size(); i++){
            if(schedule.get(date.minusDays(i)).contains(employee)){
                counter++;
            }
            else {
                return counter;
            }
        }
        return counter;
    }

    public boolean isDateInSchedule(LocalDate date){
        return schedule.keySet().contains(date);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "schedule=" + schedule +
                '}';
    }
}
