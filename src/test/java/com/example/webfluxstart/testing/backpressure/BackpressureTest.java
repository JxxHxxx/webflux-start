package com.example.webfluxstart.testing.backpressure;

import com.example.webfluxstart.testing.Backpressure;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class BackpressureTest {

    @Test
    void generate_number() {
        StepVerifier
                .create(Backpressure.generateNumber(), 1L)
                .thenConsumeWhile(num -> num >= 1)
                .expectError()
                .verifyThenAssertThat()
                .hasDroppedElements();
    }
}
