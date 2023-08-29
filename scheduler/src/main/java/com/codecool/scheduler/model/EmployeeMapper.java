package com.codecool.scheduler.model;

import com.codecool.scheduler.dto.EmployeeDTO;

public class EmployeeMapper {

    public static Employee mapDTO(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setName(employee.getName());
        return employee;
    }
}
