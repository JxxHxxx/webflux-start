package com.example.webfluxstart.annotated;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class HelloController {

    @PostMapping("/hello")
    public Mono create(@RequestBody HelloForm form) {
        Mono<Hello> hello = Mono.just(new Hello(form.id(), form.name()));
        return hello;
    }

    @PostMapping("/v2/hello")
    public Mono create(@RequestBody Mono<HelloForm> form) {
        return form.map(f -> new Hello(f.id(), f.name()))
                   .doOnNext(hello -> log.info("[ID : {} NAME : {}]", hello.getId(), hello.getName()));
    }
}
