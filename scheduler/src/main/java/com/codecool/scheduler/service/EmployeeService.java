package com.codecool.scheduler.service;

import com.codecool.scheduler.dto.EmployeeDTO;
import com.codecool.scheduler.model.Employee;
import com.codecool.scheduler.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
