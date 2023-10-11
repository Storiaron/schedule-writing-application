package com.codecool.scheduler.logic;

import com.codecool.scheduler.model.Day;
import com.codecool.scheduler.model.Employee;
import com.codecool.scheduler.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
@Component
public class ScheduleWriter {

    private final EmployeeService employeeService;
    private Map<LocalDate, List<Employee>> schedule = new HashMap<>();
    private List<Employee> employees;
    @Autowired
    public ScheduleWriter(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public Map<LocalDate, List<Employee>> createSchedule(int year, int month, List<Day> days){
        employees = employeeService.getAllEmployees();
        orderEmployeesByAvailability();
        int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
        for(Day day : days){
            scheduleOneDay(day);
        }
        return schedule;
    }
    private void orderEmployeesByAvailability(){
        Collections.sort(employees);
    }
    private void scheduleOneDay(Day day){
        List<Employee> scheduledWorkers = new ArrayList<>();
        List<Employee> availableEmployees = getAvailableEmployees(day.getDate());
        schedule.put(day.getDate(), scheduledWorkers);
    }
    private List<Employee> getAvailableEmployees(LocalDate date){
        List<Employee> availableEmployees = new ArrayList<>();
        for(Employee employee : employees){
            if(!employee.getCurrentRequests().stream().anyMatch(request -> request.getDate() == date)){
                availableEmployees.add(employee);
            }
        }
        return availableEmployees;
    }
}
