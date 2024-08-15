package com.lucasteixeira.workandwork.Resource.exception;

import com.lucasteixeira.workandwork.services.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


}
