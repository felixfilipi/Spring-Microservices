package net.springmicroservices.employeeservice.service;

import net.springmicroservices.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//This will registry our feign client to access on localhost:8080 api endpoint
//@FeignClient(url = "http://localhost:8080", value = "DEPARTMENT-SERVICE")

// Now we want to enable load balancing in department-service, so instead we call to the instance directly
// we can call it from eureka server which enable load balancing automatically, so this eureka server work as
// API Gateway for the microservice instance in some way, We just need to call the Server Name, and
// the load balancing between instances will be automatically handled by eureka server.
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {
    // build get department rest api
    @GetMapping("api/departments/{department-code}")
    DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);
}
