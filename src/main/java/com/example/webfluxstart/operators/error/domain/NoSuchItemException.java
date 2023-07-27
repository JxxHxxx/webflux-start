package com.example.webfluxstart.operators.error.domain;

public class NoSuchItemException extends RuntimeException {

    public NoSuchItemException(String message) {
        super(message);
    }
}
