package com.example.webfluxstart.reference_book.operators.error.domain;

public class NoSuchItemException extends RuntimeException {

    public NoSuchItemException(String message) {
        super(message);
    }
}
