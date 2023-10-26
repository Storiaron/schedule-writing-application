package com.codecool.scheduler.repository;

import com.codecool.scheduler.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ShiftRepository extends JpaRepository <Shift, Long> {

    Shift findShiftByShiftStartAndShiftEnd(LocalDateTime shiftStart, LocalDateTime shiftEnd);
}
