package com.example.webfluxstart.reference_book.backpressure;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * 데이터 요청 개수를 제어해서 과부하를 방지하는 방식
 */
@Slf4j
public class RequestControllerBackPressure {

    public static void main(String[] args) {
        Flux
            .range(1, 5)
            .doOnRequest(data -> log.info("# doOnRequest: {}", data))
            .subscribe(new BaseSubscriber<Integer>() {
                @Override
                protected void hookOnSubscribe(Subscription subscription) {
                    request(1);
                }

                @SneakyThrows
                @Override
                protected void hookOnNext(Integer value) {
                    Thread.sleep(2000L);
                    log.info("# hookOnNext: {}", value);
                    request(1); // 데이터 요청 개수를 제어
                }
            });
    }
}
