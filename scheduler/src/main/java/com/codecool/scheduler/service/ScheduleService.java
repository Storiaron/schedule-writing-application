package com.codecool.scheduler.service;

import com.codecool.scheduler.dto.DayDTO;
import com.codecool.scheduler.dto.ScheduleRequestDTO;
import com.codecool.scheduler.dto.ShiftDTO;
import com.codecool.scheduler.logic.ScheduleWriterFactory;
import com.codecool.scheduler.model.Day;
import com.codecool.scheduler.model.Schedule;
import com.codecool.scheduler.model.Shift;
import com.codecool.scheduler.repository.DayRepository;
import com.codecool.scheduler.repository.EmployeeRepository;
import com.codecool.scheduler.repository.ScheduleRepository;
import com.codecool.scheduler.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduleService {

    private final ScheduleWriterFactory scheduleWriterFactory;
    private final DayRepository dayRepository;
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final ShiftRepository shiftRepository;
    @Autowired
    public ScheduleService(ScheduleWriterFactory scheduleWriterFactory, DayRepository dayRepository, ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository, ShiftRepository shiftRepository) {
        this.scheduleWriterFactory = scheduleWriterFactory;
        this.dayRepository = dayRepository;
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.shiftRepository = shiftRepository;
    }

    @Transactional
    public void addDailyRequirements(List<DayDTO> dayDTOs){
        for(DayDTO dayDTO : dayDTOs){
            Day day = dayRepository.findByDate(dayDTO.getDate());
            if(day == null){
                day = new Day();
                day.setDate(dayDTO.getDate());
            }
            List<Shift> shifts = new ArrayList<>();
            for(ShiftDTO shiftDTO : dayDTO.getShifts()){
                LocalDateTime shiftStart = LocalDateTime.of(dayDTO.getDate(), LocalTime.parse(shiftDTO.getShiftStart()));
                LocalDateTime shiftEnd = LocalDateTime.of(dayDTO.getDate(), LocalTime.parse(shiftDTO.getShiftEnd()));
                Shift shift = shiftRepository.findShiftByShiftStartAndShiftEnd(shiftStart, shiftEnd);
                if(shift == null){
                    shift = new Shift();
                    shift.setShiftStart(shiftStart);
                    shift.setShiftEnd(shiftEnd);
                }
                shift.setDay(day);
                shift.setMinEmployees(shiftDTO.getMinEmployees());
                shift.setPreferredEmployees(shiftDTO.getPreferredEmployees());
                shifts.add(shift);
            }
            day.setShifts(shifts);
            dayRepository.save(day);
            shiftRepository.saveAll(shifts);
        }
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

    public List<String> getScheduleOptions(){
        return scheduleWriterFactory.getScheduleOptions();
    }
}
