package com.lucasteixeira.workandwork.Resource.exception;

import com.lucasteixeira.workandwork.services.exception.DataIntegrityViolationException;
import com.lucasteixeira.workandwork.services.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError erro = new StandardError(Instant.now(),status.value(), "Recurso não encontrado", e.getMessage(), request.getRequestURI());
        return  ResponseEntity.status(status).body(erro);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException (DataIntegrityViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError erro = new StandardError(Instant.now(),status.value(), "Violação de dados", e.getMessage(), request.getRequestURI());
        return  ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationErrors (MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationError errors = new ValidationError(Instant.now(), status.value(), "Validation erro", "erro na validação dos campos", request.getRequestURI());

        for (FieldError x : e.getFieldErrors()) {
            errors.addError(x.getField(), x.getDefaultMessage());
        }
        
        return  ResponseEntity.status(status).body(errors);
    }


}
