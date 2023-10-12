package com.codecool.scheduler.service;

import com.codecool.scheduler.logic.ScheduleWriterFactory;
import com.codecool.scheduler.model.Day;
import com.codecool.scheduler.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleService {

    private final ScheduleWriterFactory scheduleWriterFactory;
    private List<Day> days;
    @Autowired
    public ScheduleService(ScheduleWriterFactory scheduleWriterFactory) {
        this.scheduleWriterFactory = scheduleWriterFactory;
    }

    public void specifyDailyNeeds(List<Day> days){
        this.days = days;
    }
    public Map<LocalDate, List<Employee>> generateSchedule(String typeofSchedule){
        if(days != null) {
            return scheduleWriterFactory.getScheduleWriter(typeofSchedule).createSchedule(days);
        }
        else {
            return null;
        }
    }
}
