package com.example.webfluxstart.reference_book.scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SubscribeOn {

    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1, 3})
                .subscribeOn(Schedulers.boundedElastic()) // <- 라인을 제거하면 main 스레드에서 작업을 진행함
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                .doOnSubscribe(subscription -> log.info("# doOnSubscribe"))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(500L);
    }
}
