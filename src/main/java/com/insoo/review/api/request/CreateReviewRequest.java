package com.insoo.review.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateReviewRequest {

    private final Long restaurantId;
    private String content;
    private Double score;
}
