package com.codecool.scheduler.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "schedule")
    @JsonManagedReference
    private List<Day> schedule;
    private boolean saved;
    public void add(Day day){
        if(schedule == null){
            schedule = new ArrayList<>();
        }
        schedule.add(day);
    }

    public List<Day> getWorkDays(Employee employee){
        //TODO
        return null;
    }

    //TODO rename
    private boolean getWithDate(LocalDate date, Employee employee){
        for(Day day : schedule){
            if(day.getDate().equals(date)){
                return day.getShifts().stream().anyMatch(shift -> shift.getScheduledEmployees().contains(employee));
            }
        }
        return false;
    }
    public int getContinuousWorkDays(Employee employee, Day day){
        int counter = 1;
        for(int i = 1; i < schedule.size(); i++){
            if(getWithDate(day.getDate().minusDays(i), employee)){
                counter++;
            }
            else {
                return counter;
            }
        }
        return counter;
    }

    public boolean isDateInSchedule(LocalDate date){
       return schedule.stream().anyMatch(day -> day.getDate().equals(date));
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", schedule:" + schedule +
                '}';
    }
}

