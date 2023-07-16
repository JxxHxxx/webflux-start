package com.example.webfluxstart.scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Publisher란 Reactor Sequence에서 발생하는 Signal을 Downstream 으로 전송하는 주체
 * publishOn() Operator는 코드상에서 publishOn()을 기준으로 아래쪽인 Downstream의 실행 스레드를 변경합니다.
 */

@Slf4j
public class PublishOn {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                .doOnSubscribe(subscription -> log.info("# doOnSubscribe"))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(500L);
    }
}
