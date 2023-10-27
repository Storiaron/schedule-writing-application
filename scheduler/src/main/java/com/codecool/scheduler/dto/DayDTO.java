package com.codecool.scheduler.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DayDTO {
    private LocalDate date;
    private List<ShiftDTO> shifts;
}
