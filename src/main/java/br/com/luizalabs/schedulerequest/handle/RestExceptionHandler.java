package br.com.luizalabs.schedulerequest.handle;

import br.com.luizalabs.schedulerequest.exception.ExceptionDetails;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionDetails> handlerSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.newBuilder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .title("Duplicate entry 'Addressee Name'")
                        .details(ex.getMessage())
                        .message(ex.getClass().getName())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ExceptionDetails> handlerInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.newBuilder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .title("Not-null property references a transient value")
                        .details(ex.getMessage())
                        .message(ex.getClass().getName())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDetails> handlerIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.newBuilder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .title("Invalid UUID string")
                        .details(ex.getMessage())
                        .message(ex.getClass().getName())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ExceptionDetails> handlerIllegalStateException(IllegalStateException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.newBuilder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .title("No primary or single unique constructor found for interface")
                        .details(ex.getMessage())
                        .message(ex.getClass().getName())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
