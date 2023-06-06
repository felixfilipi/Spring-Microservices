package net.springmicroservices.departmentservice.service.implementation;

import lombok.AllArgsConstructor;
import net.springmicroservices.departmentservice.dto.ConvertDto;
import net.springmicroservices.departmentservice.dto.DepartmentDto;
import net.springmicroservices.departmentservice.entity.Department;
import net.springmicroservices.departmentservice.repository.DepartmentRepository;
import net.springmicroservices.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImple implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private ConvertDto convertDto;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        // convert department dto to department jpa entity
        Department department = convertDto.convertToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);

        // convert back to DTO;
        DepartmentDto savedDepartmentDto = convertDto.convertToDTO(savedDepartment);
        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findByDepartmentCode(code);

        // convert to DTO
        DepartmentDto departmentDto = convertDto.convertToDTO(department);
        return departmentDto;
    }


}
