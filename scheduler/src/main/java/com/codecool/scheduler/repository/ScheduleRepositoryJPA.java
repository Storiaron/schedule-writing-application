package com.codecool.scheduler.repository;

import com.codecool.scheduler.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepositoryJPA extends JpaRepository<Schedule, Long> {
}
