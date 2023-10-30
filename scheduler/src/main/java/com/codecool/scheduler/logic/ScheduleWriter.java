package com.codecool.scheduler.logic;

import com.codecool.scheduler.model.Day;
import com.codecool.scheduler.model.Employee;
import com.codecool.scheduler.model.Schedule;
import com.codecool.scheduler.model.Shift;
import com.codecool.scheduler.service.EmployeeService;

import java.util.*;
public class ScheduleWriter {

    protected final EmployeeService employeeService;
    protected Schedule schedule;
    protected List<Employee> employees;
    protected int shiftLength;
    public ScheduleWriter(EmployeeService employeeService, int shiftLength) {
        this.employeeService = employeeService;
        this.shiftLength = shiftLength;
        schedule = new Schedule();
    }
    public Schedule createSchedule(List<Day> days){
        employees = employeeService.getAllEmployees();
        orderEmployeesByRequestCount();
        for(Day day : days){
            scheduleOneDay(day);
        }
        return schedule;
    }
    protected void orderEmployeesByRequestCount(){
        Collections.sort(employees);
    }
    protected void scheduleOneDay(Day day){
        List<Employee> scheduledEmployees = new ArrayList<>();
        List<Employee> availableEmployees = getAvailableEmployees(day);
        for(int i = 0; i < day.getShifts().get(0).getMinEmployees() && i < availableEmployees.size(); i++){
            scheduledEmployees.add(availableEmployees.get(i));
        }
        if(day.getShifts().get(0).getMinEmployees() > scheduledEmployees.size()){
            addEmployeesWithMostRequests(day.getShifts().get(0).getMinEmployees() - scheduledEmployees.size(), scheduledEmployees);
        }
        day.getShifts().get(0).setScheduledEmployees(scheduledEmployees);
        schedule.add(day);
        scheduleShiftForEmployees(day.getShifts().get(0), scheduledEmployees);

    }
    protected List<Employee> getAvailableEmployees(Day day){
        List<Employee> availableEmployees = new ArrayList<>();
        for(Employee employee : employees){
            if(employee.isAvailable(day.getDate()) && employee.getRemainingHoursThisMonth() >= shiftLength){
                availableEmployees.add(employee);
            }
        }
        return availableEmployees;
    }

    protected void addEmployeesWithMostRequests(int num, List<Employee> scheduledEmployees){
        for(Employee employee : employees){
            if(num == 0){
                break;
            }
            if(!scheduledEmployees.contains(employee) && employee.getRemainingHoursThisMonth() >= shiftLength){
                scheduledEmployees.add(employee);
                num--;
            }
        }
    }
    protected void scheduleShiftForEmployees(Shift shift, List<Employee> scheduledEmployees){
        for(Employee employee : scheduledEmployees){
            employee.addWorkedHours(shiftLength);
            employee.getScheduledShifts().add(shift);
        }
    }
}
