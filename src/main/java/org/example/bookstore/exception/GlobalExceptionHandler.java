package org.example.bookstore.exception;

import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String TIME_STAMP = "timestamp";
    private static final String STATUS = "status";
    private static final String ERRORS = "errors";
    private static final String MESSAGE = "message";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, Object> body = Map.of(
                TIME_STAMP, LocalDateTime.now(),
                STATUS, HttpStatus.BAD_REQUEST.value(),
                ERRORS, getErrorMessages(ex)
        );

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Map<String, Object>> handleEntityNotFoundException(
            EntityNotFoundException ex) {
        Map<String, Object> body = Map.of(
                TIME_STAMP, LocalDateTime.now(),
                STATUS, HttpStatus.BAD_REQUEST.value(),
                MESSAGE, ex.getMessage()
        );

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private List<String> getErrorMessages(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .map(this::getErrorMessage)
                .collect(toList());
    }

    private String getErrorMessage(ObjectError error) {
        if (error instanceof FieldError fieldError) {
            return fieldError.getField() + ": " + fieldError.getDefaultMessage();
        }
        return error.getDefaultMessage();
    }
}
