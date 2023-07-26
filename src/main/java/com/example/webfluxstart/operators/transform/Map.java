package com.example.webfluxstart.operators.transform;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.UUID;

// 내부에서 에러 발생 시 Sequence가 종료되지 않고 계속 진행되도록 하는 기능을 지원한다.

@Slf4j
public class Map {

    public static void main(String[] args) {
        Flux.just("스타벅스 목성점", "스타벅스 지구점", "스타벅스 화성점")
                .map(storeName -> new Store(storeName))
                .subscribe(data -> log.info("# onNext: {}", data));
    }

    @Getter
    @ToString
    static class Store {
        private String id;
        private String name;

        public Store(String name) {
            this.id = UUID.randomUUID().toString().substring(0, 8);
            this.name = name;
        }
    }
}
