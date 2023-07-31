package com.example.webfluxstart.reference_book.operators.create;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
public class Defer2 {

    public static void main(String[] args) throws InterruptedException {
        log.info("# start: {}", LocalDateTime.now());
        Mono.just("Hello")
                .delayElement(Duration.ofSeconds(3))
                .switchIfEmpty(sayDefault())
//                .switchIfEmpty(Mono.defer(() -> sayDefault())) <- 불필요한 메서드 실행을 방지하려면 주석 삭제
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(3500);
    }

    private static Mono<String> sayDefault() {
        log.info("# Say Hi");
        return Mono.just("Hi");
    }
}
