package com.example.webfluxstart.exam;

import reactor.core.publisher.Flux;

public class Example5 {

    public static void main(String[] args) {
        // Flux 는 리액터에서 Publisher : 입력으로 들어오는 데이터를 제공하는 역할
        Flux<String> sequence = Flux.just("Hello", "Reactor"); // Hello, Reactor 가 입력으로 들어오는 데이터 소스
        sequence.map(data -> data.toLowerCase())
                .subscribe(data -> System.out.println(data)); // subscriber 역할

        // just, map 은 Operator 역할
    }
}
