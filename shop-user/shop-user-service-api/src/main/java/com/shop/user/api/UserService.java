package com.shop.user.api;


import com.shop.user.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/user")
public interface UserService {

    @GetMapping("/findByUserName/{name}")
    @ResponseBody
    public User findUserByUserName(@PathVariable("name") String name);

    @PostMapping("/register")
    public void registerUser(@RequestBody User user);

    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestParam("username") String username,@RequestParam("password") String password);

}
