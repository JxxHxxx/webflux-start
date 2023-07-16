package com.example.webfluxstart.scheduler;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Parallel {

    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19})
                .parallel() // 파라미터 parallelism 는 the number of parallel rails 의미함. rails는 논리적인 코어 수에 맞게 데이터를 그룹화 한것을 의미
//                .parallel(4)
                .runOn(Schedulers.parallel())
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);
    }
}
