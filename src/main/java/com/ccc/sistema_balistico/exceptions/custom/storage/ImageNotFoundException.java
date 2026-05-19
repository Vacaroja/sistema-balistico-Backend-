package com.ccc.sistema_balistico.exceptions.custom.storage;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String message) {
        super(message);
    }
}
