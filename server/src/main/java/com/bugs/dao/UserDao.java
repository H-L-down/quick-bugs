package com.bugs.dao;

import com.bugs.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    public User getUserByUserid(String id);

    void createUser(User user);
}
