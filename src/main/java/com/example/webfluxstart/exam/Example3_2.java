package com.example.webfluxstart.exam;

import reactor.core.publisher.Flux;

/**
 * 데이터 소스로 제공되는 배열 데이터를 처리하는 예제
 */
public class Example3_2 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{3, 6, 7, 9})
                .filter(num -> num > 6)
                .map(num -> num * 2)
                .subscribe(System.out::println);
    }
}
