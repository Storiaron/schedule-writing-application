package com.codecool.scheduler.repository;

import com.codecool.scheduler.model.Schedule;
import org.springframework.stereotype.Repository;

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

    public void saveSchedule(UUID scheduleId){
        for(Schedule schedule : schedulePrototypes){
            if(schedule.getId().equals(scheduleId)){
                actualSchedules.add(schedule);
                break;
            }
        }
    }
}
