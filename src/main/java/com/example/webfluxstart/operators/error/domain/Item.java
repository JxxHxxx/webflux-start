package com.example.webfluxstart.operators.error.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Item {
    private Long id;
    private String name;
    private Integer price;

    @Builder
    public Item(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
