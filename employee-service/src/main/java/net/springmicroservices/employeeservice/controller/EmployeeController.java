package net.springmicroservices.employeeservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.springmicroservices.employeeservice.dto.APIResponseDto;
import net.springmicroservices.employeeservice.dto.EmployeeDto;
import net.springmicroservices.employeeservice.entity.Employee;
import net.springmicroservices.employeeservice.exception.ErrorDetail;
import net.springmicroservices.employeeservice.exception.ResourceNotFoundException;
import net.springmicroservices.employeeservice.service.EmployeeServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "CRUD employees service",
        description = "CRUD REST APIs - user | Spring microservice"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {

    EmployeeServices employeeServices;

    @Operation(
            summary = "Create user",
            description = "Create user used to save user data to database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeServices.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployee(@PathVariable("id") Long employeeId){
        APIResponseDto apiResponseDto = employeeServices.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto newData){
        EmployeeDto updatedData = employeeServices.updateEmployee(newData);
        return new ResponseEntity<>(updatedData, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> allEmployee = employeeServices.getAllEmployee();
        return new ResponseEntity<>(allEmployee, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable("id") Long employeeId){
        employeeServices.deleteEmployee(employeeId);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException e,
                                                                       WebRequest webRequest){
        ErrorDetail errorDetail = new ErrorDetail(
                LocalDateTime.now(),
                e.getMessage(),
                webRequest.getDescription(false),
                "Employee Not Found"
        );

        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
}
