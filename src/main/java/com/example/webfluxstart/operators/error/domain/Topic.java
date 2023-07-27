package com.example.webfluxstart.operators.error.domain;

import lombok.Getter;

@Getter
public class Topic {
    private String name;

    public Topic(String name) {
        this.name = name;
    }

    public static Topic topic(String name) {
        return new Topic(name);
    }

    public boolean validate() {
        if (name.equals("spring")) {
            throw new NotAllowedException();
        }
        return true;
    }

    public String toUpper() {
        return name.toUpperCase();
    }
}
