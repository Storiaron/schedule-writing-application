package com.codecool.scheduler.logic;

import com.codecool.scheduler.model.Day;
import com.codecool.scheduler.model.Employee;
import com.codecool.scheduler.service.EmployeeService;
import java.time.LocalDate;
import java.util.*;
public class ScheduleWriter {

    protected final EmployeeService employeeService;
    protected Map<LocalDate, List<Employee>> schedule = new HashMap<>();
    protected List<Employee> employees;
    public ScheduleWriter(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public Map<LocalDate, List<Employee>> createSchedule(List<Day> days){
        employees = employeeService.getAllEmployees();
        orderEmployeesByAvailability();
        for(Day day : days){
            scheduleOneDay(day);
        }
        return schedule;
    }
    protected void orderEmployeesByAvailability(){
        Collections.sort(employees);
    }
    protected void scheduleOneDay(Day day){
        List<Employee> scheduledWorkers = new ArrayList<>();
        List<Employee> availableEmployees = getAvailableEmployees(day.getDate());
        if(availableEmployees.size() >= day.getMinEmployees()){
            if(availableEmployees.size() >= day.getPreferredEmployees()){
                scheduledWorkers.addAll(availableEmployees.subList(0, day.getPreferredEmployees() - 1));
            }
            else {
                scheduledWorkers.addAll(availableEmployees.subList(0, availableEmployees.size() - 1));
            }
        }
        else {
            availableEmployees.addAll(getEmployeesWithMostRequests(day.getMinEmployees() - availableEmployees.size() - 1));
            scheduledWorkers.addAll(availableEmployees);
        }

        schedule.put(day.getDate(), scheduledWorkers);
    }
    protected List<Employee> getAvailableEmployees(LocalDate date){
        List<Employee> availableEmployees = new ArrayList<>();
        for(Employee employee : employees){
            if(!employee.getCurrentRequests().stream().anyMatch(request -> request.getDate() == date)){
                availableEmployees.add(employee);
            }
        }
        return availableEmployees;
    }

    protected List<Employee> getEmployeesWithMostRequests(int num){
        List<Employee> forcedEmployees = new ArrayList<>();
        for(int i = 0; i < num; i++) {
            forcedEmployees.add(employees.get(i));
        }
        return forcedEmployees;
    }
}
