package com.example.webfluxstart.operators.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class OnErrorReturn {

    public static void main(String[] args) {
        Flux.just("spring", "java", null, "jpa")
                .map(topic -> topic.toUpperCase())
                .onErrorReturn("ANONYMOUS")
                .subscribe(log::info);
    }
}
