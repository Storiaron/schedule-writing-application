package com.codecool.scheduler.service;

import com.codecool.scheduler.dto.ScheduleRequestDTO;
import com.codecool.scheduler.logic.ScheduleWriterFactory;
import com.codecool.scheduler.model.Day;
import com.codecool.scheduler.model.Schedule;
import com.codecool.scheduler.repository.DayRepository;
import com.codecool.scheduler.repository.EmployeeRepository;
import com.codecool.scheduler.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduleService {

    private final ScheduleWriterFactory scheduleWriterFactory;
    private final DayRepository dayRepository;
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    @Autowired
    public ScheduleService(ScheduleWriterFactory scheduleWriterFactory, DayRepository dayRepository, ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository) {
        this.scheduleWriterFactory = scheduleWriterFactory;
        this.dayRepository = dayRepository;
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
    }

    public void specifyDailyNeeds(List<Day> days){
        dayRepository.saveAll(days);
    }
    public Schedule generateSchedule(ScheduleRequestDTO scheduleRequestDTO) {
        LocalDate startingDate = scheduleRequestDTO.getStartingDate();
        String typeofSchedule = scheduleRequestDTO.getTypeofSchedule();
        LocalDate endingDate = startingDate.plusMonths(1);
        List<Day> days = dayRepository.findAllByDateBetween(startingDate, endingDate);
        Schedule schedule = scheduleWriterFactory.getScheduleWriter(typeofSchedule).createSchedule(days);
        scheduleRepository.addPrototype(schedule);
        return schedule;
    }

    public void saveSchedule(UUID scheduleId){
        Schedule schedule = scheduleRepository.getSchedulePrototype(scheduleId);
        addWorkHours(schedule);
        scheduleRepository.saveSchedule(schedule);
    }

    private void addWorkHours(Schedule schedule){
        schedule.getSchedule().forEach((date, scheduledEmployees) -> employeeRepository.saveAll(scheduledEmployees));
    }
}
