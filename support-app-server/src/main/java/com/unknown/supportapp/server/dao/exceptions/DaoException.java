package com.unknown.supportapp.server.dao.exceptions;

public class DaoException extends RuntimeException {
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
