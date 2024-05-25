package org.backend.senebank.exceptions;

public class InsufficientFundsException extends BusinessException{
    public InsufficientFundsException(String message) {
        super(message);
    }
}
