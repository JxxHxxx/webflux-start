package com.example.webfluxstart.exam;

import reactor.core.publisher.Flux;

public class Example3 {

    public static void main(String[] args) {
        Flux.just(6, 9, 13)
                .map(num -> num % 2)
                .subscribe(System.out::println);
    }
}
