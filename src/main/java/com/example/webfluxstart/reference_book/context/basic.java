package com.example.webfluxstart.reference_book.context;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context는 Key/value 형태의 데이터를 저장할 수 있는 구조이다.
 */

@Slf4j
public class basic {

    public static void main(String[] args) throws InterruptedException {
        Mono
            .deferContextual(ctx -> Mono.just("Hello " + ctx.get("firstName")))
            .doOnNext(data -> log.info("# just doOnNext : {}", data))
            .subscribeOn(Schedulers.boundedElastic())
            .publishOn(Schedulers.parallel())
            .transformDeferredContextual((mono, ctx) -> mono.map(data -> data + " " + ctx.get("lastName")))
            .contextWrite(context -> context.put("lastName", "Jobs"))
            .contextWrite(context -> context.put("firstName", "Steve"))
            .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);


    }
}
