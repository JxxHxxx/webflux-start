package com.example.webfluxstart.backpressure;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * Downstream 데이터 처리 속도가 느려서 Upstream emit 속도를 따라가지 못할 경우 에러를 발생시키는 전략
 */

@Slf4j
public class ErrorBackpressure {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .interval(Duration.ofMillis(1L))
                .onBackpressureError()
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> {
                    try {
                        Thread.sleep(5L);
                    } catch (InterruptedException e) {
                    }
                    log.info("# onNext: {}", data);

                }, error -> log.error(" onError"));
        Thread.sleep(2000L);
        }
    }

