package com.example.webfluxstart.operators.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class Error {

    public static void main(String[] args) {
        Flux.range(1, 5)
                .flatMap(num -> {
                    if (num % 3 == 0) {
                        return Flux.error(
                                new IllegalArgumentException("Not allowed multiple of 3"));

                    } else {
                        return Mono.just(num);
                    }
                })
                .subscribe(data -> log.info("# onNext: {}", data),
                        error -> log.error("#onError: ", error));
    }
}
