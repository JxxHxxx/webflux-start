package com.example.webfluxstart.reference_book.operators.error;

import com.example.webfluxstart.reference_book.operators.error.domain.Item;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
public class OnErrorResume {

    private static Map<Long, Item> itemsLocalCache = Map.of(
            1l, Item.builder().id(1l).name("마라탕").price(15000).build(),
            2l, Item.builder().id(2l).name("차돌짬뽕").price(12000).build(),
            3l, Item.builder().id(3l).name("연어 포케").price(10000).build()
    );

    private static Map<Long, Item> itemDatabase = Map.of(
            1l, Item.builder().id(1l).name("마라탕").price(15000).build(),
            2l, Item.builder().id(2l).name("차돌짬뽕").price(12000).build(),
            3l, Item.builder().id(3l).name("연어 포케").price(10000).build(),
            4l, Item.builder().id(4l).name("짜장면").price(9000).build()
    );

    public static void main(String[] args) {
        Long requestItemId = 4l;

        Mono.just(requestItemId)
                .map(id -> itemsLocalCache.get(id))
                .onErrorResume(NullPointerException.class, error ->
                        Mono.just(requestItemId)
                            .doOnNext(data -> log.info("캐시에 데이터가 존재하지 않아 DB를 조회합니다."))
                            .map(id -> itemDatabase.get(id)))
                .subscribe(data -> log.info("data {}", data));
    }
}
