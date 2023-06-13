package net.springmicroservices.employeeservice.dto;

import net.springmicroservices.employeeservice.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvertDto {

    public EmployeeDto convertToDto(Employee employee){
        EmployeeDto newDto = new EmployeeDto(
                employee.getId(),
                employee.getFirst_name(),
                employee.getLast_name(),
                employee.getEmail(),
                employee.getDepartmentCode(),
                employee.getOrganizationCode()
        );
        return newDto;
    }

    public Employee convertToEmployee(EmployeeDto employeeDto){
        Employee newEmployee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirst_name(),
                employeeDto.getLast_name(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                employeeDto.getOrganizationCode()
        );
        return newEmployee;
    }

}
