package br.com.luizalabs.schedulerequest.exception;

import org.springframework.http.HttpStatus;

public class ContactNumberException extends DomainException {
    public ContactNumberException(Class<?> classType, String message) {
        super(HttpStatus.BAD_REQUEST.value(),  classType, message);
    }
}
