package com.codecool.scheduler.repository;

import com.codecool.scheduler.model.Employee;
import com.codecool.scheduler.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RequestRepository extends JpaRepository<Request, Long> {

    Request findByDateAndEmployee(LocalDate date, Employee employee);
}
