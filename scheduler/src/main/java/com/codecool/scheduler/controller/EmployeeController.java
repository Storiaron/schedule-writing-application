package com.codecool.scheduler.controller;

import com.codecool.scheduler.dto.EmployeeDTO;
import com.codecool.scheduler.dto.LoginDTO;
import com.codecool.scheduler.model.Employee;
import com.codecool.scheduler.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping("")
    public void addEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.addEmployee(employeeDTO);
    }
    @GetMapping("")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{employeeName}")
    public Employee getEmployee(@PathVariable String employeeName){
        return employeeService.getEmployee(employeeName);
    }
    @PutMapping("/{employeeName}")
    public Employee getEmployeeScheduledShifts(@PathVariable String employeeName, @RequestBody LocalDate date){
        return employeeService.getEmployeeScheduledShifts(employeeName, date);
    }
    @GetMapping("/reset")
    public void resetWorkHours(){
        employeeService.resetWorkHours();
    }
}
