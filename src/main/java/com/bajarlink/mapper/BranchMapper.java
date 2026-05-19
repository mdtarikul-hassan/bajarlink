package com.bajarlink.mapper;

import com.bajarlink.model.Branch;
import com.bajarlink.model.Store;
import com.bajarlink.payload.dto.BranchDto;

public class BranchMapper {
    public static BranchDto toDto(Branch branch) {
        return BranchDto.builder()
                .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .email(branch.getEmail())
                .workingDays(branch.getWorkingDays())
                .openTime(branch.getOpenTime())
                .closeTime(branch.getCloseTime())
                .createdAt(branch.getCreatedAt())
                .updatedAt(branch.getUpdatedAt())
                .storeId(branch.getStore().getId())
                .build();
    }
    public static Branch toEntity(BranchDto dto, Store store) {
        return Branch.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .store(store)
                .workingDays(dto.getWorkingDays())
                .openTime(dto.getOpenTime())
                .closeTime(dto.getCloseTime())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}
