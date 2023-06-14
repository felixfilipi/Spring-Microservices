package net.springmicroservices.employeeservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Microservice Documentation",
				description = "Spring Microservice Documentation can be accessed in localhost:8081/swagger-ui/index.html",
				version = "v1.0",
				contact = @Contact(
						name = "Felix",
						email = "felixfilipi4@gmail.com",
						url = "https://felixfilipi@github.com/felixfilipi"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://felixfilipi@github.com/felixfilipi"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring microservice user Management documentation",
				url = "https://felixfilipi@github.com/felixfilipi"
		)
)
@EnableFeignClients
//@EnableEurekaClient
// USE SPRING CLOUD V2021 AND USE ENABLE EUREKA CLIENT ANNOTATION, FOR SERVICES, IDK WHY
// BUT THE AUTO ENABLED EUREKA CLIENT ANNOTATION V2022 NOT WORKING HERE, BUT WE CAN USE
// V2022 FOR CONFIG SERVER AND API GATEWAY
public class EmployeeServiceApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
