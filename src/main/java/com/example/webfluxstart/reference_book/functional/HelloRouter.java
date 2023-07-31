package com.example.webfluxstart.reference_book.functional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RouterFunctions.*;

@Configuration("helloRouterV1")
public class HelloRouter {

    @Bean
    public RouterFunction<?> routeHello(HelloHandler helloHandler) {
        return route().POST("/v3/hello", helloHandler::create).build();
    }
}
