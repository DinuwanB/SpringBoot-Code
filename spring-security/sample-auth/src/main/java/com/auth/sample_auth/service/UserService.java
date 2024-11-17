package com.auth.sample_auth.service;

import com.auth.sample_auth.dto.UserDto;
import com.auth.sample_auth.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
