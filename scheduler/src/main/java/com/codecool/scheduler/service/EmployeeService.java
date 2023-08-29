package com.codecool.scheduler.service;

import com.codecool.scheduler.dto.EmployeeDTO;
import com.codecool.scheduler.model.EmployeeMapper;
import com.codecool.scheduler.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addEmployee(EmployeeDTO employeeDTO){
        employeeRepository.save(EmployeeMapper.mapDTO(employeeDTO));
    }
}
