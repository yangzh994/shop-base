package com.shop.user.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.user.api.UserService;
import com.shop.user.api.mapper.UserMapper;
import com.shop.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUserName(@PathVariable("name") String name) {
//        System.out.println(name);
//        User user = new User();
//        user.setId(1);
//        user.setUsername(name);
//        user.setPassword("123456");
//        user.setPhone("15807430822");
//        return user;
        QueryWrapper query = new QueryWrapper();
        query.eq("username",name);
        User user = userMapper.selectOne(query);
        return user;
    }

    @Override
    public void saveUser(@RequestBody User user) {
        System.out.println(user);
        userMapper.insert(user);
    }
}
