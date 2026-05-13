package com.bajarlink.controller;

import com.bajarlink.exception.UserException;
import com.bajarlink.mapper.UserMapper;
import com.bajarlink.model.User;
import com.bajarlink.payload.dto.UserDto;
import com.bajarlink.payload.response.AuthResponse;
import com.bajarlink.repo.UserRepo;
import com.bajarlink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws UserException, Exception {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }
}
