package com.bajarlink.payload.dto;

import com.bajarlink.model.Order;
import com.bajarlink.model.Product;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private Long id;

    private Integer quantity;

    private Double price;

    private ProductDto product;

    private Long productId;

}
