package com.example.webfluxstart.scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Single {
    public static void main(String[] args) throws InterruptedException {
        doTask("task1", Schedulers.single())
                .subscribe(data -> log.info("# onNext: {}", data));

        doTask("task2", Schedulers.boundedElastic())
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(200L);
    }

    private static Flux<Integer> doTask(String taskName, Scheduler scheduler) {
        return Flux.fromArray(new Integer[]{1,3,5,7})
                .publishOn(scheduler)
                .filter(data -> data < 3)
                .doOnNext(data -> log.info("# {} doOnNext filter: {}", taskName, data))
                .map(data -> data * 10)
                .doOnNext(data -> log.info("# {} doOnNext map : {}", taskName, data));
    }
}
