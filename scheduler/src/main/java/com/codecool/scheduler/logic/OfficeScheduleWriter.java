package com.codecool.scheduler.logic;

import com.codecool.scheduler.model.Day;
import com.codecool.scheduler.model.Employee;
import com.codecool.scheduler.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

public class OfficeScheduleWriter extends ScheduleWriter{
    public OfficeScheduleWriter(EmployeeService employeeService, int shiftLength) {
        super(employeeService, shiftLength);
    }

    @Override
    protected void scheduleOneDay(Day day){
        List<Employee> scheduledWorkers = new ArrayList<>();
        List<Employee> availableEmployees = getAvailableEmployees(day.getDate());
        schedule.put(day, scheduledWorkers);
    }
}
