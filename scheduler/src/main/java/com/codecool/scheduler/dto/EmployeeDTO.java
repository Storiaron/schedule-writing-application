package com.codecool.scheduler.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {

    private String name;

    public EmployeeDTO(String name) {
        this.name = name;
    }

}
