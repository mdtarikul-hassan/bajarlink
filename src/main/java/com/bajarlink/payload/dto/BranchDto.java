package com.bajarlink.payload.dto;

import com.bajarlink.model.Store;
import com.bajarlink.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchDto {

    private Long id;

    private String name;

    private String address;

    private String phone;

    private String email;


    private List<String> workingDays;

    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private StoreDto store;

    private Long storeId;

    private UserDto manager;
}
