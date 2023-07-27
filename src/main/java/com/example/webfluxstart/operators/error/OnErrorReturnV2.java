package com.example.webfluxstart.operators.error;

import com.example.webfluxstart.operators.error.domain.NotAllowedException;
import com.example.webfluxstart.operators.error.domain.Topic;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

import static com.example.webfluxstart.operators.error.domain.Topic.*;

@Slf4j
public class OnErrorReturnV2 {

    public static void main(String[] args) {
        List<Topic> topics = List.of(topic("spring"), topic(null), topic("java"), topic("jpa"));

        Flux.fromIterable(topics)
                .filter(topic -> topic.validate())
                .map(topic -> topic.toUpper())
                .onErrorReturn(NullPointerException.class,"ANONYMOUS")
                .onErrorReturn(NotAllowedException.class,"spring is not allowed")
                .subscribe(log::info);
    }
}
