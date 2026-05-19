package com.bajarlink.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(unique = true, nullable = false)
    @Email(message = "Email should be valid")
    private String email;

    @ElementCollection
    private List<String> workingDays;

    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private Store store;

    @ManyToOne
    private User manager;

    @PrePersist
    protected void oncreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onupdate() {
        updatedAt = LocalDateTime.now();
    }

}
