package com.codecool.scheduler.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {
    static Schedule scheduleNoWorkDays = new Schedule();
    static Schedule scheduleWithWorkDays = new Schedule();
    static Employee employee = new Employee();
    static Employee employee2 = new Employee();
    @BeforeAll
    static void setupNoWorkDays(){

        Day day1 = new Day();
        Day day2 = new Day();
        Day day3 = new Day();
        Day day4 = new Day();
        Day day5 = new Day();
        Shift shiftNoEmployee = new Shift();
        shiftNoEmployee.setScheduledEmployees(new ArrayList<>());

        day1.setShifts(List.of(shiftNoEmployee));
        day1.setDate(LocalDate.of(2023,10,01));
        day2.setShifts(List.of(shiftNoEmployee));
        day2.setDate(LocalDate.of(2023,10,02));
        day3.setShifts(List.of(shiftNoEmployee));
        day3.setDate(LocalDate.of(2023,10,03));
        day4.setShifts(List.of(shiftNoEmployee));
        day4.setDate(LocalDate.of(2023,10,04));
        day5.setShifts(List.of(shiftNoEmployee));
        day5.setDate(LocalDate.of(2023,10,05));
        scheduleNoWorkDays.setSchedule(List.of(day1,day2,day3,day4,day5));
        employee.setName("Test");
        Long id = new Long(1);
        employee.setId(id);
    }
    @BeforeAll
    static void setupWorkDaysSchedule(){
        employee2.setName("Test");
        Long id = new Long(1);
        employee2.setId(id);
        Day day1 = new Day();
        Day day2 = new Day();
        Day day3 = new Day();
        Day day4 = new Day();
        Day day5 = new Day();
        Shift shiftWithTestedEmployee = new Shift();
        shiftWithTestedEmployee.setScheduledEmployees(List.of(employee2));

        day1.setShifts(List.of(shiftWithTestedEmployee));
        day1.setDate(LocalDate.of(2023,10,01));
        day2.setShifts(List.of(shiftWithTestedEmployee));
        day2.setDate(LocalDate.of(2023,10,02));
        day3.setShifts(List.of(shiftWithTestedEmployee));
        day3.setDate(LocalDate.of(2023,10,03));
        day4.setShifts(List.of(shiftWithTestedEmployee));
        day4.setDate(LocalDate.of(2023,10,04));
        day5.setShifts(List.of(shiftWithTestedEmployee));
        day5.setDate(LocalDate.of(2023,10,05));
        scheduleWithWorkDays.setSchedule(List.of(day1,day2,day3,day4,day5));

    }
    @Test
    void getWorkDays() {
    }

    @Test
    void getContinuousWorkDaysNegative() {
        Day day = new Day();
        day.setDate(LocalDate.of(2023, 10, 06));
        int result = scheduleNoWorkDays.getContinuousWorkDays(employee, day);
        int expected = 1;
        assertEquals(expected, result);
    }
    @Test
    void getContinuousWorkDaysPositive() {
        Day day = new Day();
        day.setDate(LocalDate.of(2023, 10, 06));
        int result = scheduleWithWorkDays.getContinuousWorkDays(employee2, day);
        int expected = 6;
        assertEquals(expected, result);
    }
}