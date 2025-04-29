package com.example.java01jwt.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameExistedException extends AuthenticationException {
    /**
     * Constructs an {@code AuthenticationException} with the specified message and no
     * root cause.
     *
     * @param msg the detail message
     */
    public UsernameExistedException(String msg) {
        super(msg);
    }
}
