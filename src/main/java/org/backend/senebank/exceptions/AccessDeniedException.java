package org.backend.senebank.exceptions;

public class AccessDeniedException extends BusinessException {
    public AccessDeniedException(String message) {
        super(message);
    }
}
