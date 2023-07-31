package com.example.webfluxstart.reference_book.operators.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class OnErrorContinue {

    public static void main(String[] args) {
        Flux
            .just(1,2,4,0,6,12)
            .map(num -> 12 / num)
            .onErrorContinue(ArithmeticException.class, (error, num) ->
                    log.error("error : {}, num: {}", error.getMessage(), num))
            .subscribe(
                    data -> log.info("# onNext: {}", data),
                    error -> log.error("# onError:" , error)
            );
    }
}
