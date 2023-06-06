package net.springmicroservices.employeeservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ErrorDetail {
    private LocalDateTime timestamp;
    private String Message;
    private String path;
    private String errorCode;
}
