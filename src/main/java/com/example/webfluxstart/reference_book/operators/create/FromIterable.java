package com.example.webfluxstart.reference_book.operators.create;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class FromIterable {

    public static void main(String[] args) {
        Flux
            .fromIterable(People.samples())
            .subscribe(people -> log.info("이름 {}, 나이 {}", people.getName(), people.getAge())
            );
    }

    @Getter
    static class People {
        String name;
        Integer age;

        public People(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public static List<People> samples() {
            return List.of(
                    new People("jxx", 15),
                    new People("xuni", 24),
                    new People("yani", 21),
                    new People("shy", 39));
        }
    }
}
