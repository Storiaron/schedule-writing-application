package com.codecool.scheduler.repository;

import com.codecool.scheduler.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Employee save(Employee employee);
}
