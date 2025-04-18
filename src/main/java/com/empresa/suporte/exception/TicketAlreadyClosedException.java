package com.empresa.suporte.exception;

public class TicketAlreadyClosedException extends RuntimeException {
    public TicketAlreadyClosedException(String message) {
        super(message);
    }
}
