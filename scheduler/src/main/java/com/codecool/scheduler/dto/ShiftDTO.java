package com.codecool.scheduler.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShiftDTO {
    private String shiftStart;
    private String shiftEnd;
    private int minEmployees;
    private int preferredEmployees;
}
