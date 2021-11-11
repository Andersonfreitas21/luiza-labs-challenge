package br.com.luizalabs.schedulerequest.handle;

import br.com.luizalabs.schedulerequest.exception.DataIntegrityViolationExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<DataIntegrityViolationExceptionDetails> handlerSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        return new ResponseEntity<>(
                DataIntegrityViolationExceptionDetails.newBuilder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .title("Duplicate entry 'Addressee Name'")
                        .details(ex.getMessage())
                        .message(ex.getClass().getName())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
