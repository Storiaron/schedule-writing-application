package com.codecool.scheduler.logic;

import com.codecool.scheduler.repository.EmployeeRepository;
import com.codecool.scheduler.service.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ScheduleWriterTest {

    ScheduleWriter scheduleWriter;


    @Autowired
    public ScheduleWriterTest (EmployeeRepository employeeRepository){
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        scheduleWriter = new ScheduleWriter(employeeService);
    }

    @Test
    void createSchedule() {
        System.out.println("hi");
    }
}