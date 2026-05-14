package com.ccc.sistema_balistico.exceptions.custom.storage;

public class FileTooLargeException extends RuntimeException {
    public FileTooLargeException(String message) {
        super(message);
    }
}
