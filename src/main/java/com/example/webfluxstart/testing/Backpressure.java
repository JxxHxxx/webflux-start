package com.example.webfluxstart.testing;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class Backpressure {

    public static Flux<Integer> generateNumber() {
        return Flux.create(
                emitter -> {
                    for (int i = 1; i < 100; i++) {
                        emitter.next(i);
                    }
                    emitter.complete();
                }, FluxSink.OverflowStrategy.ERROR);
    }
}
