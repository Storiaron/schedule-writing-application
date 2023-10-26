package com.codecool.scheduler.model;


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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "schedule_entries",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_entry_id"))
    private List<ScheduleEntry> schedule;
    public void put(Day day, List<Employee> employees){
        ScheduleEntry scheduleEntry = new ScheduleEntry();
        scheduleEntry.setDay(day);
        scheduleEntry.setScheduledEmployees(employees);
        schedule.add(scheduleEntry);
    }

    public List<Day> getWorkDays(Employee employee){
        List<Day> workdays = new ArrayList<>();
        for(ScheduleEntry scheduleEntry : schedule){
            if(scheduleEntry.getScheduledEmployees().contains(employee)){
                workdays.add(scheduleEntry.getDay());
            }
        }
        return workdays;
    }

    //TODO rename
    public List<Employee> getWithDate(LocalDate date){
        for(ScheduleEntry scheduleEntry : schedule){
            if(scheduleEntry.getDay().getDate().equals(date)){
                return scheduleEntry.getScheduledEmployees();
            }
        }
        return new ArrayList<>();
    }
    public int getContinuousWorkDays(Employee employee, Day day){
        int counter = 1;
        for(int i = 1; i < schedule.size(); i++){
            if(getWithDate(day.getDate().minusDays(i)).contains(employee)){
                counter++;
            }
            else {
                return counter;
            }
        }
        return counter;
    }

    public boolean isDateInSchedule(LocalDate date){
       return schedule.stream().anyMatch(entry -> entry.getDay().getDate().equals(date));
    }
}

