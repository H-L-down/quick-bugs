package com.bugs.controller;

import com.bugs.dao.UserDao;
import com.bugs.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// 这是一个测试用的controller
@RestController
@RequestMapping("/TestController")
public class TestController {
    @Autowired
    UserDao userDao;
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable String id) {
        User user = userDao.getUserByUserid(id);
        return user;
    }
}
