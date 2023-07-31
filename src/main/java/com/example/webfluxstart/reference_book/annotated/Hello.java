package com.example.webfluxstart.reference_book.annotated;


import lombok.Getter;

@Getter
public class Hello {
    private Long id;
    private String name;

    public Hello(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
