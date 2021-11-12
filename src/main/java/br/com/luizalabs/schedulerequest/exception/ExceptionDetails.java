package br.com.luizalabs.schedulerequest.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder(builderMethodName = "newBuilder")
public class ExceptionDetails {
    private String title;
    private int status;
    private String details;
    private String message;
    private LocalDateTime timestamp;
}
