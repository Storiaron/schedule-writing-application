package com.codecool.scheduler.repository;

import com.codecool.scheduler.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository <Shift, Long> {
}
