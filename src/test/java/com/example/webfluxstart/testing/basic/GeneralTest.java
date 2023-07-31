package com.example.webfluxstart.testing.basic;

import com.example.webfluxstart.reference_book.testing.General;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class GeneralTest {

    @Test
    void sayHelloTest() {
        StepVerifier.create(General.sayHello())
                .expectSubscription()
                .as("# expect subscriber")
                .expectNext("Hello") // 예상
                .as("# expect Hello")
                .expectNext("Reactor")
                .as("# expect Reactor")
                .verifyComplete();
    }

    @Test
    void divideByTwoTest() {
        Flux<Integer> source = Flux.just(2, 4, 6, 8, 10);
        StepVerifier
                .create(General.divideByTwo(source))
                .expectSubscription()
//                .expectNext(1)
//                .expectNext(2)
//                .expectNext(3)
//                .expectNext(4)
                .expectNext(1,2,3,4)
                .expectError() // 0으로 나눠서
                .verify();
    }

    @Test
    void takeNumberTest() {
        Flux<Integer> source = Flux.range(0, 1000);
        StepVerifier
                .create(General.takeNumber(source, 500), StepVerifierOptions.create().scenarioName("Verify from 0 to 499"))
                .expectSubscription()
                .expectNext(0) // 0 emit
                .expectNextCount(498)
                .expectNext(499)
                .expectComplete() // 더 이상 emitted 데이터가 없음 -> onComplete Signal이 전송됨
                .verify();
    }

}
