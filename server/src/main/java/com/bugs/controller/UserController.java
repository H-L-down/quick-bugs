package com.bugs.controller;

import com.bugs.controller.dto.UserDto;
import com.bugs.controller.result.StatusCode;
import com.bugs.controller.result.Result;
import com.bugs.dao.UserDao;
import com.bugs.domain.User;
import com.bugs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/UserController")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(User user) {
        UserDto userDto = userService.login(user);
        return Result.createResult(StatusCode.SUCCESS, userDto,  "登录成功");
    }

    @PostMapping("/register")
    public Result register(User user) {
        userService.register(user);
        return Result.createResult(StatusCode.SUCCESS, null, "注册成功");
    }
}
