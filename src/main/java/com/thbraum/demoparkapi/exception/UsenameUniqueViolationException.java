package com.thbraum.demoparkapi.exception;


public class UsenameUniqueViolationException extends RuntimeException {
    public UsenameUniqueViolationException(String message) {
        super(message);
    }
}
