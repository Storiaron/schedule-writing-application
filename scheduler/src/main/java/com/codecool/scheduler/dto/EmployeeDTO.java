package com.codecool.scheduler.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {

    private String name;
    private List<LocalDate> dayOffRequests;

}
