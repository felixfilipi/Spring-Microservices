package net.springmicroservices.departmentservice.dto;

import net.springmicroservices.departmentservice.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class ConvertDto {
    public DepartmentDto convertToDTO(Department department){
        DepartmentDto newDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return newDto;
    }

    public Department convertToDepartment(DepartmentDto dto){
        Department newDepartment = new Department(
                dto.getId(),
                dto.getDepartmentName(),
                dto.getDepartmentDescription(),
                dto.getDepartmentCode()
        );
        return newDepartment;
    }
}
