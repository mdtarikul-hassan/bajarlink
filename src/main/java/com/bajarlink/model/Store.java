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
@EqualsAndHashCode
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brand;

    @OneToOne
    private User storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StoreStatus status;

    private StoreContact contact = new StoreContact();

    @PrePersist
    protected void oncreate() {
        createdAt = LocalDateTime.now();
        status = StoreStatus.PENDING;
    }

//    @PrePersist
//    protected void onupdate() {
//        updatedAt = LocalDateTime.now();
//    }

}
