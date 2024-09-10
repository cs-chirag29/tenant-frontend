package com.example.rentmate.service;

import com.example.rentmate.dto.UserLoginDto;
import com.example.rentmate.exception.ResourceNotFoundException;
import com.example.rentmate.model.UserLogin;

import java.util.List;
import java.util.Optional;

public interface UserLoginService {
     List<UserLogin> getAllUserLogins();
    UserLogin createUserLogin(UserLoginDto userLoginDto) throws ResourceNotFoundException;
    boolean isUserAlreadyRegistered(String userName);

    UserLogin updateUserLogin(long id, UserLoginDto userLoginDto) throws ResourceNotFoundException;
    UserLogin getUserLoginById(long id) throws ResourceNotFoundException;

    void deleteUserLogin(long id) throws ResourceNotFoundException;
    Optional<UserLogin> authenticateUser(String username);

}

