package com.example.webfluxstart.reference_book.scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Blocking {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .publishOn(Schedulers.parallel())
                .doOnNext(data -> {
                    // 비동기적으로 실행될 작업
                    log.info("data : ", data);

                }).blockLast();
//                .blockLast(); // 블로킹하여 모든 작업이 완료될 때까지 기다림
    }
}
