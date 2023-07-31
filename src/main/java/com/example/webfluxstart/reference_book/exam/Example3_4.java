package com.example.webfluxstart.reference_book.exam;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Example3_4 {
    public static void main(String[] args) {
        Flux.concat(
                Flux.just("Mercury", "Venus", "Earth"),
                Flux.just("Mars", "Jupiter", "Saturn"),
                Flux.just("Uranus", "Neptune", "Pluto"))
                .collectList()
                .subscribe(planets -> System.out.println(planets));

        List<String> solarSystem = new ArrayList<>();

        Flux.concat(
                Flux.just("Mercury", "Venus", "Earth"),
                Flux.just("Mars", "Jupiter", "Saturn"),
                Flux.just("Uranus", "Neptune", "Pluto"))
                .subscribe(planet -> {
                    System.out.println("태양계에 행성을 추가 합니다." + planet);
                    solarSystem.add(planet);
                });
    }
}
