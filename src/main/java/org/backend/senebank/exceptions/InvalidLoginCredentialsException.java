package org.backend.senebank.exceptions;

public class InvalidLoginCredentialsException extends BusinessException {
    public InvalidLoginCredentialsException(String message) {
        super(message);
    }
}
