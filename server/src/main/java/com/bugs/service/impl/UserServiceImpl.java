package com.bugs.service.impl;

import com.bugs.controller.dto.UserDto;
import com.bugs.dao.UserDao;
import com.bugs.domain.User;
import com.bugs.exception.ServiceException;
import com.bugs.service.UserService;
import com.bugs.utils.jwtUtils;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    // 登录
    @Override
    public UserDto login(User user) {
        User dbUser = userDao.getUserByUserid(user.getId());
        if (dbUser == null) {
            // 用户不存在
            throw new ServiceException("用户名不存在");
        }
        if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new ServiceException(("用户名或密码错误"));
        }
        // 生成token
        String token = jwtUtils.generateToken(dbUser.getId());
        UserDto userDto = UserDto.createUserDto(dbUser, token);
        return userDto;
    }

    @Override
    public void register(User user) {
        if (StringUtils.isNullOrEmpty(user.getId())) {
            throw new ServiceException("用户名不能为空");
        }
        if (StringUtils.isNullOrEmpty(user.getPassword())) {
            throw new ServiceException("密码不能为空");
        }
        User dbUser = userDao.getUserByUserid(user.getId());
        if (dbUser != null) {
            throw new ServiceException("该用户已经存在");
        }
        // 开始创建
        userDao.createUser(user);
    }
}
