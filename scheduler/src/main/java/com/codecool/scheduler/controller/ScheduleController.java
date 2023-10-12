package com.codecool.scheduler.controller;

import com.codecool.scheduler.model.Day;
import com.codecool.scheduler.model.Employee;
import com.codecool.scheduler.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("")
    public void specifyDailyNeeds(@RequestBody List<Day> days){
        scheduleService.specifyDailyNeeds(days);
    }
    @PutMapping("/generate")
    public Map<LocalDate, List<Employee>> generateSchedule(@RequestBody String typeofSchedule){
        return scheduleService.generateSchedule(typeofSchedule);
    }
}
