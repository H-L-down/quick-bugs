package com.bugs.exception;

import org.springframework.stereotype.Service;

public class ServiceException extends RuntimeException{
    public ServiceException(String msg) {
        super(msg);
    }
}
