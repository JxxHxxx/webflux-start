package com.example.webfluxstart.exam.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResult<T> {
    private Integer status;
    private String message;
    private T response;

    public ApiResult(Integer status, String message, T response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }
}
