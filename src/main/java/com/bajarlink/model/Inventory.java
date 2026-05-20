package com.bajarlink.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Branch branch;

    @ManyToOne
    private Product product;

    private Integer quantity;

    private LocalDateTime lastUpdate;

    @PrePersist
    @PreUpdate
    protected void onupdate() {
        lastUpdate = LocalDateTime.now();
    }

}
