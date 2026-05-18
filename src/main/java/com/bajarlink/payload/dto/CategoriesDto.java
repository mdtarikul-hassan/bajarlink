package com.bajarlink.payload.dto;

import com.bajarlink.model.Store;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriesDto {

    private Long id;

    private String name;

    private Long storeId;
}
