package com.insoo.review.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Builder
@Getter
public class RestaurantView {

    private Long id;
    private String name;
    private String address;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
