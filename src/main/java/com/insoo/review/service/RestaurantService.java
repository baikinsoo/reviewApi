package com.insoo.review.service;

import com.insoo.review.api.request.CreateAndEditRestaurantRequest;
import com.insoo.review.api.request.CreateAndEditRestaurantRequestMenu;
import com.insoo.review.api.response.RestaurantDetailView;
import com.insoo.review.api.response.RestaurantView;
import com.insoo.review.model.MenuEntity;
import com.insoo.review.model.RestaurantEntity;
import com.insoo.review.repository.MenuRepository;
import com.insoo.review.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public RestaurantEntity createRestaurant(
            CreateAndEditRestaurantRequest request
    ) {


        RestaurantEntity restaurantEntity = RestaurantEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
        restaurantRepository.save(restaurantEntity);


        request.getMenus().forEach((menu) ->



        {
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurantEntity.getId())
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .createdAt(ZonedDateTime.now())
                    .updatedAt(ZonedDateTime.now())
                    .build();
            menuRepository.save(menuEntity);
        });


        return restaurantEntity;
    }

    @Transactional
    public void editRestaurant(
            Long restaurantId,
            CreateAndEditRestaurantRequest request
    ) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new RuntimeException("없는 레스토랑입니다."));
        restaurantEntity.changeNameAndAddress(request.getName(), request.getAddress());
        restaurantRepository.save(restaurantEntity);

        List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);
        menuRepository.deleteAll(menus);

        request.getMenus().forEach((menu) -> {
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurantId)
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .createdAt(ZonedDateTime.now())
                    .updatedAt(ZonedDateTime.now())
                    .build();

            menuRepository.save(menuEntity);
        });
    }

    @Transactional
    public void deleteRestaurant(Long restaurantId) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(restaurantId).orElseThrow();
        restaurantRepository.delete(restaurantEntity);

        List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);
        menuRepository.deleteAll(menus);
    }

    @Transactional(readOnly = true)
    public List<RestaurantView> getAllRestaurants() {
        List<RestaurantEntity> restaurantEntities = restaurantRepository.findAll();

        return restaurantEntities.stream().map((restaurantEntity) -> RestaurantView.builder()
                .id(restaurantEntity.getId())
                .name(restaurantEntity.getName())
                .address(restaurantEntity.getAddress())
                .createdAt(restaurantEntity.getCreatedAt())
                .updatedAt(restaurantEntity.getUpdatedAt())
                .build()
        ).toList();
    }

    @Transactional(readOnly = true)
    public RestaurantDetailView getRestaurantDetail(Long restaurantId) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(restaurantId).orElseThrow();
        List<MenuEntity> menuEntities = menuRepository.findAllByRestaurantId(restaurantId);

        return RestaurantDetailView.builder()
                .id(restaurantEntity.getId())
                .name(restaurantEntity.getName())
                .address(restaurantEntity.getAddress())
                .createdAt(restaurantEntity.getCreatedAt())
                .updatedAt(restaurantEntity.getUpdatedAt())
                .menus(menuEntities.stream().map((menu) -> RestaurantDetailView.Menu.builder()
                        .id(menu.getId())
                        .name(menu.getName())
                        .price(menu.getPrice())
                        .createdAt(menu.getCreatedAt())
                        .updatedAt(menu.getUpdatedAt())
                        .build()
                ).toList()
                )
                .build();
    }

}
