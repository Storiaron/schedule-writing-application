package com.codecool.scheduler.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    static Employee employee = new Employee();
    static Employee employeeWithTonOfRequests = new Employee();
    @BeforeAll
    static void setup() {
        Request request = new Request();
        request.setEmployee(employee);
        request.setDate(LocalDate.of(2023, 10, 01));
        request.setId(new Long(1));
        employee.setRequests(Set.of(request));
        employeeWithTonOfRequests.setRequests(Set.of(new Request(), new Request(), new Request(), new Request()));
    }
    @ParameterizedTest
    @ValueSource(strings = { "2023-10-02", "2023-11-15", "2023-12-25" , "1999-01-01"})
    void isAvailableTrue(LocalDate date) {
        boolean result = employee.isAvailable(date);
        assertTrue(result);
    }
    @Test
    void isisAvailableFalse(){
        boolean result = employee.isAvailable(LocalDate.of(2023, 10, 01));
        assertFalse(result);
    }

    @Test
    void compareTo() {
        int result = employee.compareTo(employeeWithTonOfRequests);
        assert(result > 0);
    }
}