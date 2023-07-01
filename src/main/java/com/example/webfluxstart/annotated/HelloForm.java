package com.example.webfluxstart.annotated;

public record HelloForm(
        Long id,
        String name
) {
    public static HelloForm of(Long id, String name) {
        return new HelloForm(id, name);
    }
}
