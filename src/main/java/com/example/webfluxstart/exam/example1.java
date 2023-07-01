package com.example.webfluxstart.exam;

import reactor.core.publisher.Mono;

public class example1 {
    public static void main(String[] args) {
        // 데이터를 한 건도 emit하지 경우
        Mono.empty()
                .subscribe(
                        data -> System.out.println("# emitted onNext signal"), // onNext
                        error -> {}, // OnError
                        () -> System.out.println("# emitted onComplete signal") // onCompleted
                );
        System.out.println("============================구분선==========================");
        // 데이터를 emit 한 경우
        Mono.just("hey")
                .subscribe(
                        data -> System.out.println("# emitted onNext signal " + data),
                        error -> {},
                        () -> System.out.println("# emitted onComplete signal")
                );

        /**
         * 사용하는 이유 : 작업이 끝났음을 알리고 이에 따른 후처리를 하고 싶을 때
         */
    }
}
