package com.example.webfluxstart.testing.timebased;

import com.example.webfluxstart.testing.TimeBased;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;

public class TimeBasedTest {

    @Test
    void getCOVID19Count() {
        StepVerifier
                .withVirtualTime(() -> TimeBased.getCOVID19Count(
                                Flux.interval(Duration.ofHours(1)).take(1)
                        )

                )
                .expectSubscription()
                .then(() -> VirtualTimeScheduler
                        .get()
                        .advanceTimeBy(Duration.ofHours(1))) // 미래의 시간을 당김
                .expectNextCount(11)
                .expectComplete()
                .verify();
    }

    @Test
    void getCOVID19Count_2() {
        StepVerifier
                .withVirtualTime(() -> TimeBased.getCOVID19Count(
                                Flux.interval(Duration.ofMinutes(1)).take(1)
                        )

                )
                .expectSubscription()
                .then(() -> VirtualTimeScheduler
                        .get()
                        .advanceTimeBy(Duration.ofSeconds(62)))
                .expectNextCount(11)
                .expectComplete()
                .verify(Duration.ofSeconds(3));
    }

    @Test
    void get_vote_count() {
        StepVerifier
                .withVirtualTime(() -> TimeBased.getVoteCount(
                                Flux.interval(Duration.ofMinutes(1))
                        )
                )
                .expectSubscription()
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNextCount(5)
                .expectComplete()
                .verify();
    }
}
