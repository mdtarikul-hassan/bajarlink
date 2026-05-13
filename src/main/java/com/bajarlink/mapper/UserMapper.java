package com.bajarlink.mapper;

import com.bajarlink.model.User;
import com.bajarlink.payload.dto.UserDto;

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
        return userDto;
    }
}
