package com.bahadir.bookshopAPI.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(String format) {
        super(format);
    }
}
