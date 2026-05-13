package com.bajarlink.controller;

import com.bajarlink.exception.UserException;
import com.bajarlink.payload.dto.UserDto;
import com.bajarlink.payload.response.AuthResponse;
import com.bajarlink.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody UserDto userDto) throws UserException {
        return ResponseEntity.ok(authService.signup(userDto));
    }

    @RequestMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserDto userDto) throws UserException {
        return ResponseEntity.ok(authService.login(userDto));
    }
}
