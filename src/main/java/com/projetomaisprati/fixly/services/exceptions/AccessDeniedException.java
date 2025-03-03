package com.projetomaisprati.fixly.services.exceptions;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String msg) {
        super(msg);
    }
}
