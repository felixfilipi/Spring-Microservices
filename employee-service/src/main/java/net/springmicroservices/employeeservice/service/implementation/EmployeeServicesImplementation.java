package net.springmicroservices.employeeservice.service.implementation;

import lombok.AllArgsConstructor;
import net.springmicroservices.employeeservice.dto.APIResponseDto;
import net.springmicroservices.employeeservice.dto.ConvertDto;
import net.springmicroservices.employeeservice.dto.DepartmentDto;
import net.springmicroservices.employeeservice.dto.EmployeeDto;
import net.springmicroservices.employeeservice.entity.Employee;
import net.springmicroservices.employeeservice.exception.ResourceNotFoundException;
import net.springmicroservices.employeeservice.repository.EmployeeRepository;
import net.springmicroservices.employeeservice.service.APIClient;
import net.springmicroservices.employeeservice.service.EmployeeServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServicesImplementation implements EmployeeServices {

    private EmployeeRepository employeeRepository;
    private ConvertDto convertDto;

    private RestTemplate restTemplate;  // only support sync
    private WebClient webClient;        // support sync, async, and streaming
    private APIClient apiClient;        // use openfeign interface

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = convertDto.convertToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = convertDto.convertToDto(savedEmployee);
        return savedEmployeeDto;
    }

    public APIResponseDto getEmployeeById(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );

//        ---------------------------
//        using RestTemplate
//        ---------------------------
//        RestTemplate is in maintenance, and going to be deprecated in future, so spring teams recommend to use WebClient,
//        because it support sync, async, streaming communication.
//        ---------------------------

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();


//        ---------------------------
//        using WebClient
//        ---------------------------

//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        apiClient
        EmployeeDto employeeDto = convertDto.convertToDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setEmployeeDto(employeeDto);
//        Mapping using modelMapper can be implemented directly in services, placing it on convertDto return null exception
//        EmployeeDto savedEmployee = modelMapper.map(employee, EmployeeDto.);

        return apiResponseDto;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepository.findById(employeeDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeDto.getId())
        );

        existingEmployee.setEmail(employeeDto.getEmail());
        existingEmployee.setFirst_name(employeeDto.getFirst_name());
        existingEmployee.setLast_name(employeeDto.getLast_name());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return convertDto.convertToDto(updatedEmployee);
    }

    public List<EmployeeDto> getAllEmployee(){
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> convertDto.convertToDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(Long employeeId){
        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );

        employeeRepository.deleteById(employeeId);
    }
}
