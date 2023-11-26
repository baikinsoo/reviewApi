package com.insoo.review.service.dto;

import com.insoo.review.model.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class ReviewDto {
    private Double avgScore;
    private List<ReviewEntity> reviews;
    private ReviewDtoPage page;

    @AllArgsConstructor
    @Builder
    @Getter
    public static class ReviewDtoPage {

        private Integer offset;

        private Integer limit;
    }
}
