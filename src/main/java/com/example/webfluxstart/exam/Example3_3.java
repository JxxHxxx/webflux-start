package com.example.webfluxstart.exam;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Example3_3 {
    public static void main(String[] args) {
        Flux<String> flux = Mono
                .justOrEmpty("Steve")
                // concatWith() 을 호출하는 Publisher 와 concatWith() 의 파라미토로 전달되는 Publisher 가
                // 각각 emit 하는 데이털들을 하나로 연결해서 새로운 Publisher의 데이터 소스로 만들어줌
                .concatWith(Mono.justOrEmpty("Jobs"));

        flux.subscribe(data -> System.out.println(data));
    }
}
