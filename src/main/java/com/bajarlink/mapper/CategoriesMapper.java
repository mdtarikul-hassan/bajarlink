package com.bajarlink.mapper;

import com.bajarlink.model.Categories;
import com.bajarlink.payload.dto.CategoriesDto;

public class CategoriesMapper {

    public static CategoriesDto toDto(Categories categories) {
        CategoriesDto dto = CategoriesDto.builder()
                .id(categories.getId())
                .name(categories.getName())
                .storeId(categories.getStore() != null ? categories.getStore().getId(): null)
                .build();
        return dto;
    }
//    public static Categories toEntity(CategoriesDto dto) {
//        Categories categories = Categories.builder()
//                .id(dto.getId())
//                .name(dto.getName())
////                .store(dto)
//                .build();
//        return categories;
//    }
}
