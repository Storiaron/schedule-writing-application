package com.codecool.scheduler.logic;

import com.codecool.scheduler.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleWriterFactory {

    private final EmployeeService employeeService;
    @Autowired
    public ScheduleWriterFactory(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
