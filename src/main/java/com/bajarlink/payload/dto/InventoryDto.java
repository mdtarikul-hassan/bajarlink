package com.bajarlink.payload.dto;

import com.bajarlink.model.Branch;
import com.bajarlink.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDto {

    private Long id;


    private BranchDto branch;

    private Long branchId;

    private Long productId;

    private ProductDto product;

    private Integer quantity;

    private LocalDateTime lastUpdate;
}
