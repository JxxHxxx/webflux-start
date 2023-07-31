package com.example.webfluxstart.reference_book.exam.dto;

public record StudyProductResponse(
        String studyProductId,
        String name,
        String category,
        String creator,
        String thumbnail
) {
}
