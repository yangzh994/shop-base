package com.shop.user.api;


import com.shop.user.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/user")
public interface UserService {

    @GetMapping("/findByUserName/{name}")
    @ResponseBody
    public User findUserByUserName(@PathVariable("name") String name);

    @PostMapping("/save")
    public void saveUser(@RequestBody User user);

}
