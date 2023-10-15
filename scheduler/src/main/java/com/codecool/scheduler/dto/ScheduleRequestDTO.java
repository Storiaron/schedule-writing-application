package com.codecool.scheduler.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ScheduleRequestDTO {
    private LocalDate startingDate;
    private String typeofSchedule;
}
