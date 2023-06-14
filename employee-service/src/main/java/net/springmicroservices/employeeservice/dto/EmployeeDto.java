package net.springmicroservices.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "EmployeeDto Model Information"
)
public class EmployeeDto {
    private Long Id;
    @Schema(
            description = "Employee First Name"
    )
    private String first_name;
    @Schema(
            description = "Employee Last Name"
    )
    private String last_name;
    @Schema(
            description = "Employee Email"
    )
    private String email;
    private String departmentCode;
    private String organizationCode;
}
