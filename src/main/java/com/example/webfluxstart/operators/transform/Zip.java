package com.example.webfluxstart.operators.transform;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
public class Zip {

    public static void main(String[] args) throws InterruptedException {
        Mono.zip(
                // 외부 API를 통해 가져와야 한다고 상상해보자
                Mono.just(new Member("jxx", 30)).delayElement(Duration.ofMillis(2500L)),
                // 애플리케이션 서버와 연동된 DB에서 조회한 결과로 가져와야 한다고 상상해보자
                Mono.just(new Order()).delayElement(Duration.ofMillis(1000L))
        )
        .map(tuple -> new OrderResponse(
                tuple.getT1().getName(),
                tuple.getT2().getId(),
                tuple.getT2().getOrderTime())
        )
        .subscribe(res -> log.info("# onNext: {}", res));

        Thread.sleep(3000L);
    }

    @Getter
    @ToString
    static class Member {
        private String name;
        private Integer age;

        public Member(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }

    @Getter
    @ToString
    static class Order {
        private String id;
        private LocalDateTime orderTime;

        public Order() {
            this.id = UUID.randomUUID().toString();
            this.orderTime = LocalDateTime.now();
        }
    }

    @Getter
    @ToString
    static class OrderResponse {
        String name;
        String orderId;
        LocalDateTime localDateTime;

        public OrderResponse(String name, String orderId, LocalDateTime localDateTime) {
            this.name = name;
            this.orderId = orderId;
            this.localDateTime = localDateTime;
        }
    }
}
