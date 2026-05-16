package com.bajarlink.payload.dto;

import com.bajarlink.model.Store;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private String sku;

    private double mrp;

    private double sellingPrice;

    private String brand;

    private String image;

    private Long storeId;
    private Long categoryId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
