package com.example.webfluxstart.reference_book.operators.create;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Just {

    public static void main(String[] args) {
        Mono
            .justOrEmpty(null)
            .subscribe(
                    data -> {},
                    error -> {},
                    () -> log.info("# onComplete"));
    }
}
