package com.example.webfluxstart.exam;

import com.example.webfluxstart.exam.dto.ApiResult;
import com.example.webfluxstart.exam.dto.StudyProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.List;

public class Example2_2 {

    public static void main(String[] args) {
        URI studyProducts = UriComponentsBuilder.newInstance().scheme("http")
                .host("jsmtmt.shop")
                .port(80)
                .path("study-products")
                .build()
                .encode()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Mono.just(
                restTemplate
                        .exchange(studyProducts,
                                HttpMethod.GET,
                                new HttpEntity<String>(headers),
                                ApiResult.class)
        ).map(res -> {
            ApiResult<List<StudyProductResponse>> apiResult = res.getBody();
            List<StudyProductResponse> response = apiResult.getResponse();
            return response;
        }).subscribe(
                data -> System.out.println("# emitted data: " + data),
                error -> {
                    System.out.println(error);
                },
                () -> System.out.println("# emitted onComplete signal")
        );
    }
}
