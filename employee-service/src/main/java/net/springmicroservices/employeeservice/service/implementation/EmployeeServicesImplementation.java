package net.springmicroservices.employeeservice.service.implementation;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import net.springmicroservices.employeeservice.dto.*;
import net.springmicroservices.employeeservice.entity.Employee;
import net.springmicroservices.employeeservice.exception.ResourceNotFoundException;
import net.springmicroservices.employeeservice.repository.EmployeeRepository;
import net.springmicroservices.employeeservice.service.APIClient;
import net.springmicroservices.employeeservice.service.EmployeeServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServicesImplementation.class);

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = convertDto.convertToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = convertDto.convertToDto(savedEmployee);
        return savedEmployeeDto;
    }

    @Override

//    change state in open, closed, and half open
//    @CircuitBreaker(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment")

//    Retry the connection for set of time (set on 5 times) (exist in config file)
//    After 5 retry of connection, and it return fail, it will go into fallback method and return default method
    @Retry(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public APIResponseDto getEmployeeById(Long employeeId){
        LOGGER.info("inside getEmployeeById() method");
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

        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8083/api/organiztions/" , employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

//        ---------------------------
//        Synchronous call using Spring Cloud OpenFeign
//        ---------------------------
//        When to Use WebClient vs OpenFeign?
//
//        1. Use WebClient when you want to use Spring Reactive Stream API, or in short
//        Reactive Programming. Reactive Programming is form of async programming which
//        execution being triggered by the arrival of data to execute on. It's push based.
//        The ordinary async call usually based on pull-based call, which doesn't react to
//        data arriving, instead it gets ready to process data and after that request the data.
//        2. Use WebClient when need non-blocking HTTP request, otherwise use Feign due to its simple usage.

//        ---------------------------
//        OpenFeign helps us to simplify client code to talk to the RESTful web services.
//        ---------------------------

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

//        ---------------------------
//        ModelMapper
//        ---------------------------
//        Mapping using modelMapper can be implemented directly in services, placing it on convertDto return null exception
//        EmployeeDto savedEmployee = modelMapper.map(employee, EmployeeDto.);

        EmployeeDto employeeDto = convertDto.convertToDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setOrganizationDto(organizationDto);

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
    public APIResponseDto getDefaultDepartment(Long employeeId){
        LOGGER.info("inside getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentDescription("Research & Development Department");

        EmployeeDto employeeDto = convertDto.convertToDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setEmployeeDto(employeeDto);

        return apiResponseDto;
    }
}
