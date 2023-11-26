package com.insoo.review.api.request;

import com.insoo.review.model.MenuEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAndEditRestaurantRequestMenu {

    private String name;
    private Integer price;

}
