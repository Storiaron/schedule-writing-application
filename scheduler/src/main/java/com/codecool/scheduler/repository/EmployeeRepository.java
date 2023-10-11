package com.codecool.scheduler.repository;

import com.codecool.scheduler.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByName(String name);
}
