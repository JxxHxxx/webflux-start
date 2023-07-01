package com.example.webfluxstart.exam.dto;

public record StudyProductResponse(
        String studyProductId,
        String name,
        String category,
        String creator,
        String thumbnail
) {
}
