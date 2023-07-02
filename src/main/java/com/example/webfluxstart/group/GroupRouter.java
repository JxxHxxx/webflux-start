package com.example.webfluxstart.group;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.*;

@Configuration
public class GroupRouter {

    @Bean
    public RouterFunction<?> routerGroup(GroupHandler handler) {
        return route()
                .POST("/groups", handler::create)
                .GET("/groups/{group-id}", handler::findOne)
                .build();
    }
}
