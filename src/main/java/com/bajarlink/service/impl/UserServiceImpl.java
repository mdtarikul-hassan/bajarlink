package com.bajarlink.service.impl;

import com.bajarlink.config.JwtProvider;
import com.bajarlink.exception.UserException;
import com.bajarlink.model.User;
import com.bajarlink.repo.UserRepo;
import com.bajarlink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.net.UnknownServiceException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final JwtProvider jwtProvider;

    @Override
    public User getUserFromJwtToken(String token) throws UserException {
        String email = jwtProvider.getEmailFromToken(token);
        User user = userRepo.findByEmail(email);
        if(user == null){
            throw new UserException("User not found");
        }
        return user;
    }

    @Override
    public User getCurrentUser() throws UserException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email);
        if(user == null){
            throw new   UserException("User not found");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        User user = userRepo.findByEmail(email);
        if(user == null){
            throw new UserException("User not found");
        }
        return user;
    }

    @Override
    public User getUserById(Long id) throws Exception {
        return userRepo.findById(id).orElseThrow(
                () -> new Exception("User not found with id: " + id)
        );
    }

    @Override
    public List<User> getAllUsers() {
        return  userRepo.findAll();
    }
}
