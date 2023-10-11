package com.codecool.scheduler.service;

import com.codecool.scheduler.dto.EmployeeDTO;
import com.codecool.scheduler.model.Employee;
import com.codecool.scheduler.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employeeRepository.save(employee);
    }
    public Employee loginEmployee(EmployeeDTO employeeDTO){
        Employee employee = employeeRepository.findByName(employeeDTO.getName());
        if(employee == null){
            employee = new Employee();
            employee.setName("not found");
        }
        return employee;
    }
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
}
