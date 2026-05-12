package com.bajarlink.service;

import com.bajarlink.exception.UserException;
import com.bajarlink.payload.dto.UserDto;
import com.bajarlink.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse signup(UserDto userDto) throws UserException;
    AuthResponse login(UserDto userDto) throws UserException;
}
