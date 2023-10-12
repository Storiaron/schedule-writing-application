package com.codecool.scheduler.logic;

import com.codecool.scheduler.model.Day;
import com.codecool.scheduler.model.Employee;
import com.codecool.scheduler.service.EmployeeService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScheduleWriterSingleShift extends ScheduleWriter{
    public ScheduleWriterSingleShift(EmployeeService employeeService, int shiftLength) {
        super(employeeService, shiftLength);
    }

    @Override
    protected void scheduleOneDay(Day day){
        List<Employee> scheduledWorkers = new ArrayList<>();
        List<Employee> availableEmployees = getAvailableEmployees(day.getDate());
        schedule.put(day.getDate(), scheduledWorkers);
    }
}
