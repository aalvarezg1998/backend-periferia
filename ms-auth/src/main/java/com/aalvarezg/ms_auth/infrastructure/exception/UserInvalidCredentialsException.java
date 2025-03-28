package com.aalvarezg.ms_auth.infrastructure.exception;

public class UserInvalidCredentialsException extends RuntimeException {
    public UserInvalidCredentialsException() {
        super("Invalid credentials");
    }
}
