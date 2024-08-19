package com.lucasteixeira.workandwork.services.exception;

public class DataIntegrityViolationException extends RuntimeException{

    public DataIntegrityViolationException(String msg) {
        super(msg);
    }
}
