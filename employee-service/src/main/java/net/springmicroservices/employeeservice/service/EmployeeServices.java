package net.springmicroservices.employeeservice.service;

import net.springmicroservices.employeeservice.dto.APIResponseDto;
import net.springmicroservices.employeeservice.dto.EmployeeDto;
import net.springmicroservices.employeeservice.entity.Employee;

import java.util.List;

public interface EmployeeServices {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long employeeId);
    EmployeeDto updateEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getAllEmployee();
    void deleteEmployee(Long employeeId);
}
