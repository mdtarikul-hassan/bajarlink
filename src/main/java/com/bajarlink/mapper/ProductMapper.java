package com.bajarlink.mapper;

import com.bajarlink.model.Categories;
import com.bajarlink.model.Product;
import com.bajarlink.model.Store;
import com.bajarlink.payload.dto.CategoriesDto;
import com.bajarlink.payload.dto.ProductDto;

public class ProductMapper {

    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setSku(product.getSku());
        dto.setMrp(product.getMrp());
        dto.setSellingPrice(product.getSellingPrice());
        dto.setBrand(product.getBrand());
        dto.setImage(product.getImage());
        dto.setStoreId(product.getStore() != null ? product.getStore().getId() : null);
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        dto.setCategoriesDto(CategoriesMapper.toDto(product.getCategory()));
        return dto;

    }
    public static Product toEntity(ProductDto dto, Store store, Categories categories) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setSku(dto.getSku());
        product.setMrp(dto.getMrp());
        product.setSellingPrice(dto.getSellingPrice());
        product.setBrand(dto.getBrand());
        product.setImage(dto.getImage());
        product.setStore(store);
        product.setCategory(categories);

        return  product;
    }
}
