package com.bugs.service;

import com.bugs.controller.dto.UserDto;
import com.bugs.domain.User;

public interface UserService {
    UserDto login(User user);
    void register(User user);
}
