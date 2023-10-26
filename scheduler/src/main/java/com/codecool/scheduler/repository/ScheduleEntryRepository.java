package com.codecool.scheduler.repository;

import com.codecool.scheduler.model.ScheduleEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleEntryRepository extends JpaRepository<ScheduleEntry, Long> {
}
