package com.bajarlink.mapper;

import com.bajarlink.model.OrderItem;
import com.bajarlink.payload.dto.OrderItemDto;

public class OrderItemMapper {
    public static OrderItemDto toDto(OrderItem items) {
        if(items==null){
            return null;
        }
        return OrderItemDto.builder()
                .id(items.getId())
                .productId(items.getProduct().getId())
                .quantity(items.getQuantity())
                .price(items.getPrice())
                .product(ProductMapper.toDto(items.getProduct()))
                .build();
    }
}
