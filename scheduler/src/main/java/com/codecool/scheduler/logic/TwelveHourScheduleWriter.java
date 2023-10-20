package com.codecool.scheduler.logic;

import com.codecool.scheduler.model.Day;
import com.codecool.scheduler.model.Employee;
import com.codecool.scheduler.service.EmployeeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TwelveHourScheduleWriter extends ScheduleWriter{
    public TwelveHourScheduleWriter(EmployeeService employeeService, int shiftLength) {
        super(employeeService, shiftLength);
    }
    @Override
    protected List<Employee> getAvailableEmployees(LocalDate date){
        int daysWorkableInRow = 4;
        List<Employee> availableEmployees = new ArrayList<>();
        for(Employee employee : employees){
            if(employee.isAvailable(date) &&
                    employee.getRemainingHoursThisMonth() >= shiftLength &&
                    isBelowLimit(employee, date, daysWorkableInRow)
            ){
                availableEmployees.add(employee);
            }
        }
        return availableEmployees;
    }

    protected boolean isBelowLimit(Employee employee, LocalDate date, int streakLimit){
        return schedule.getContinuousWorkDays(employee, date) < streakLimit;
    }
}
