package com.codecool.scheduler.repository;

import com.codecool.scheduler.model.Schedule;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Repository
public class ScheduleRepository {

    private Set<Schedule> schedulePrototypes = new HashSet<>();
    private Set<Schedule> actualSchedules = new HashSet<>();

    public void addPrototype(Schedule schedule){
        schedulePrototypes.add(schedule);
    }

    public void addFinalSchedule(Schedule schedule){
        actualSchedules.add(schedule);
    }

    public Schedule getSchedulePrototype(UUID scheduleId){
        for(Schedule schedule : schedulePrototypes){
            if(schedule.getId().equals(scheduleId)){
                return schedule;
            }
        }
        return null;
    }
    public Schedule getScheduleByDate(LocalDate date){
        for(Schedule schedule : actualSchedules){
            if(schedule.isDateInSchedule(date)){
                return schedule;
            }
        }
        return null;
    }
    public void saveSchedule(Schedule schedule){
        actualSchedules.add(schedule);
    }
}
