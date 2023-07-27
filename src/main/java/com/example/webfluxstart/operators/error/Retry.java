package com.example.webfluxstart.operators.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Retry {

    public static void main(String[] args) throws InterruptedException {
        final int[] count = {1};

        Flux.range(1, 3)
                .delayElements(Duration.ofSeconds(1)) // 1초 마다 데이터 emit
                .map(num -> {
                    try {
                        if (num == 3 && count[0] < 3) {
                            count[0]++;
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {}
                    return num;
                })
                .timeout(Duration.ofMillis(1500)) // 작업이 1.5초 이상 걸리면 인터럽트가 발생
                .retry(1) // 에러 발생 시 1번 재시도
                .subscribe(
                        data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: ", error),
                        () -> log.info("#onComplete"));

        Thread.sleep(17000);
    }
}
