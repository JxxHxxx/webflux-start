package com.example.webfluxstart.reference_book.backpressure;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
/**
 * Downstream 으로 전달할 데이터가 버퍼에 가득 찰 경우, 가버퍼 안에 채워진 데이터 중에 가장 오래된 데이터를 드랍한다.
 */
@Slf4j
public class BufferDropOldestBackPressure {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .interval(Duration.ofMillis(300L))
                .doOnNext(data -> log.info("# emitted by original Flux: {}", data))
                .onBackpressureBuffer(
                        2,
                        dropped -> log.info("# Overflow & Dropped: {} **", dropped),
                        BufferOverflowStrategy.DROP_OLDEST)
                .doOnNext(data -> log.info(" # [ emitted by Buffer: {} ]", data))
                .publishOn(Schedulers.parallel(), false, 1)
                .subscribe(data -> {
                            try {
                                Thread.sleep(1000L);
                            } catch (InterruptedException e) {
                                log.info("# onNext: {}", data);
                            }
                        },
                        error -> log.error("# onError", error));

        Thread.sleep(3000L);
        log.info("# Finished");
    }
}
