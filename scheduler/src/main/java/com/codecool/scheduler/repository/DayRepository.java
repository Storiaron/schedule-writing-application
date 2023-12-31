package com.codecool.scheduler.repository;

import com.codecool.scheduler.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DayRepository extends JpaRepository<Day, Long> {

    List<Day> findAllByDateBetween(LocalDate startOfMonth, LocalDate endOfMonth);

    Day findByDate(LocalDate date);
}
