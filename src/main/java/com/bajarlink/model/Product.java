package com.bajarlink.model;

import com.bajarlink.domain.StoreStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private double mrp;

    @Column(nullable = false)
    private double sellingPrice;

    @Column(nullable = false)
    private String brand;

    private String image;

    @ManyToOne
    private Categories category;

    @ManyToOne
    private Store store;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void oncreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onupdate() {
        updatedAt = LocalDateTime.now();
    }
}
