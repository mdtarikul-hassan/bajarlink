package com.bajarlink.mapper;

import com.bajarlink.model.Branch;
import com.bajarlink.model.Inventory;
import com.bajarlink.model.Product;
import com.bajarlink.payload.dto.InventoryDto;

public class InventoryMapper {

    public static InventoryDto toDto(Inventory inventory) {
        return InventoryDto.builder()
                .id(inventory.getId())
                .productId(inventory.getProduct().getId())
                .product(ProductMapper.toDto(inventory.getProduct()))
                .branchId(inventory.getBranch().getId())
                .quantity(inventory.getQuantity())
                .lastUpdate(inventory.getLastUpdate())
                .build();
    }

    public static Inventory toEntity(InventoryDto dto,Product product, Branch branch) {
        return Inventory.builder()
                .quantity(dto.getQuantity())
                .branch(branch)
                .product(product)
                .lastUpdate(dto.getLastUpdate())
                .build();

    }
}
