package com.insoo.review.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantDetailView {
    private Long id;
    private String name;
    private String address;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private List<Menu> menus;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Menu {
        private Long id;
        private String name;
        private Integer price;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
    }

}
