package com.tomassirio.ataccamascanner.exceptions;

import com.tomassirio.ataccamascanner.utils.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InstanceNotFoundException.class)
    public ResponseEntity<?> instanceNotFoundExceptionHandler(InstanceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "001",
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InstanceInfoValidationException.class)
    public ResponseEntity<?> instanceInfoValidationExceptionHandler(InstanceInfoValidationException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "002", ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistingInstanceException.class)
    public ResponseEntity<?> existingInstanceExceptionHandler(ExistingInstanceException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "003", ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TestConnectionException.class)
    public ResponseEntity<?> testConnectionExceptionHandler(TestConnectionException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "004", ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "500", ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}