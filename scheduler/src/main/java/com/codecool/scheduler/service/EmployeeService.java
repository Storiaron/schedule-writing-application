package com.codecool.scheduler.service;

import com.codecool.scheduler.dto.EmployeeDTO;
import com.codecool.scheduler.model.Employee;
import com.codecool.scheduler.repository.EmployeeRepository;
import com.codecool.scheduler.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ScheduleRepository scheduleRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ScheduleRepository scheduleRepository) {
        this.employeeRepository = employeeRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public void addEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setName(employeeDTO.getUsername());
        employee.setRole(employeeDTO.getRole());
        employee.setHoursPerMonth(employeeDTO.getHoursPerMonth());
        employee.setPassword(employee.getPassword());
        employeeRepository.save(employee);
    }
    public Employee loginEmployee(EmployeeDTO employeeDTO){
        return employeeRepository.findByName(employeeDTO.getUsername());
    }
    @Transactional
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployee(String employeeName){
        return employeeRepository.findByName(employeeName);
    }
    public Employee getEmployeeScheduledShifts(String employeeName, LocalDate date){

        //TODO add method
        return null;
    }

    public void resetWorkHours(){
        List<Employee> employees  = employeeRepository.findAll();
        employees.forEach(employee -> employee.resetWorkHours());
        employeeRepository.saveAll(employees);
    }
}
