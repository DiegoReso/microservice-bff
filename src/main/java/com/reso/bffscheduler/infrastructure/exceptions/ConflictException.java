package com.reso.bffscheduler.infrastructure.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String messsage, Throwable throwable){
        super(messsage);
    }
}
