package com.codecool.scheduler.controller;

import com.codecool.scheduler.dto.EmployeeDTO;
import com.codecool.scheduler.model.Employee;
import com.codecool.scheduler.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/reset")
    public void resetWorkHours(){
        employeeService.resetWorkHours();
    }
    @GetMapping()
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Employee> loginEmployee(@RequestBody EmployeeDTO employeeDTO){
        Employee employee = employeeService.loginEmployee(employeeDTO);
        //TODO check on frontend if response status was ok
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }
}
