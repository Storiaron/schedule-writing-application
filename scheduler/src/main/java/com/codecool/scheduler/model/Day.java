package com.codecool.scheduler.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class Day {

    private LocalDate date;
    private int minEmployees;
    private int preferredEmployees;
    //for now when employee numbers are not specified it will default to 1/1
    public Day() {
        this.minEmployees = 1;
        this.preferredEmployees = 1;
    }
}
