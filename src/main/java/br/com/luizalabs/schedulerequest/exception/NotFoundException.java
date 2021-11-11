package br.com.luizalabs.schedulerequest.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends DomainException {
    public NotFoundException(Class<?> classType, String message) {
        super(HttpStatus.NOT_FOUND.value(), classType, message);
    }
}
