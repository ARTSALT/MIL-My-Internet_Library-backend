package com.project.mil.domain.book;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookRequestDTO(
    String title,
    @JsonProperty("author_name")
    String authorName,
    @JsonProperty("publish_year")
    Integer publishYear,
    String synopsis,
    String isbn
) {
    
}
