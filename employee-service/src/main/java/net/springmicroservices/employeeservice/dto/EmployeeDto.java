package net.springmicroservices.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDto {
    private Long Id;
    private String first_name;
    private String last_name;
    private String email;
    private String departmentCode;
    private String organizationCode;
}
