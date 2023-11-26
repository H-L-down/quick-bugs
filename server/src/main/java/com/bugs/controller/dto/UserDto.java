package com.bugs.controller.dto;

import com.bugs.domain.User;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String token;

    public static UserDto createUserDto(User user, String token) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setToken(token);
        return userDto;
    }
}