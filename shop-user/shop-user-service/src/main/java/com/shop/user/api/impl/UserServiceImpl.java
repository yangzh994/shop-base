package com.shop.user.api.impl;

import com.shop.user.api.UserService;
import com.shop.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findUserByUserName(@PathVariable("name") String name) {
        System.out.println(name);
        User user = new User();
        user.setId(1);
        user.setUsername(name);
        user.setPassword("123456");
        user.setPhone("15807430822");
        return user;
    }
}
