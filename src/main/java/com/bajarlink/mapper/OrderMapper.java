package com.bajarlink.mapper;

import com.bajarlink.model.Order;
import com.bajarlink.payload.dto.OrderDto;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .branchId(order.getBranch().getId())
                .customer(order.getCustomer())
                .paymentType(order.getPaymentType())
                .createdAt(order.getCreatedAt())
                .cashier(UserMapper.toDTO(order.getCashier()))
                .items(order.getItems().stream()
                        .map(OrderItemMapper::toDto)
                        .collect(Collectors.toList()))
                .build();

    }
}
