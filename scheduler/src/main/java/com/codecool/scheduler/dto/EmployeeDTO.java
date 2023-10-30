package com.codecool.scheduler.dto;

import com.codecool.scheduler.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
    private String username;
    private String password;
    private Role role;
}
