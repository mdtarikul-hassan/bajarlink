package com.bajarlink.mapper;

import com.bajarlink.model.User;
import com.bajarlink.payload.dto.UserDto;

import java.time.LocalDateTime;

public class UserMapper {
    public static UserDto toDTO(User savedUser) {
        if(savedUser == null){
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setFullName(savedUser.getFullName());
        userDto.setEmail(savedUser.getEmail());
        userDto.setPassword(savedUser.getPassword());
        userDto.setRole(savedUser.getRole());
        userDto.setPhoneNumber(savedUser.getPhoneNumber());
        userDto.setCreatedAt(savedUser.getCreatedAt());
        userDto.setUpdatedAt(savedUser.getUpdatedAt());
        userDto.setLastLogin(savedUser.getLastLogin());
        userDto.setBranchId(savedUser.getBranch() != null ? savedUser.getBranch().getId() : null);
        userDto.setStoreId(savedUser.getStore() != null ? savedUser.getStore().getId() : null);
        return userDto;
    }
    public static User toEntity(UserDto dto) {
        User user = new User();
//        user.setId(dto.getId());
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(dto.getUpdatedAt());
        user.setLastLogin(dto.getLastLogin());
        return user;
    }
}
