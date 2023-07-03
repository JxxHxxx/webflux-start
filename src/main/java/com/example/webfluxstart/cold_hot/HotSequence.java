package com.example.webfluxstart.cold_hot;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * Hot은 무언가를 새로 시작하지 않는 것입니다.
 */

@Slf4j
public class HotSequence {
    public static void main(String[] args) throws InterruptedException {
        String[] singers = {"Singer A", "Singer B", "Singer C", "Singer D", "Singer E"};

        log.info("# Begin concert:");
        Flux<String> concertFlux = Flux
                .fromArray(singers)
                .delayElements(Duration.ofSeconds(1)) // emit을 일정시간 동안 지연시키는 Operator
                .share(); // cold sequence change to hot sequence

        concertFlux.subscribe(
                singer -> log.info("# Subscriber1 is watching {}'s song", singer)
        );

        Thread.sleep(2500L);

        concertFlux.subscribe(
                singer -> log.info("# Subscriber2 is watching {}'s song", singer)
        );

        Thread.sleep(3000L);
        log.info("# End concert:");

    }
}
