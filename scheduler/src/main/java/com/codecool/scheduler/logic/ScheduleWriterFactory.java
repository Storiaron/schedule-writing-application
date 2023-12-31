package com.codecool.scheduler.logic;

import com.codecool.scheduler.model.Employee;
import com.codecool.scheduler.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class ScheduleWriterFactory {

    private final EmployeeService employeeService;
    @Autowired
    public ScheduleWriterFactory(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public ScheduleWriter getScheduleWriter(String typeofSchedule){
        int hoursPerStandardShift = 8;
        switch(typeofSchedule){
            case "12h, single shift" -> {
                return new TwelveHourScheduleWriter(employeeService, 12);
            }
            default -> {
                return new ScheduleWriter(employeeService, hoursPerStandardShift);
            }
        }
    }

    public List<String> getScheduleOptions(){
        return List.of("default", "12h, single shift");
    }
}
