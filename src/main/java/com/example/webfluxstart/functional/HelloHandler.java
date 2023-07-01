package com.example.webfluxstart.functional;

import com.example.webfluxstart.annotated.Hello;
import com.example.webfluxstart.annotated.HelloForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@Component("helloHandlerV1")
public class HelloHandler {

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(HelloForm.class)
                .map(form -> new Hello(form.id(), form.name()))
                .doOnNext(hello -> log.info("[FUNCTIONAL EP ID : {} NAME : {}]", hello.getId(), hello.getName()))
                .flatMap(hello -> ServerResponse
                        .created(URI.create("/v3/hello"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(hello)));
    }
}
