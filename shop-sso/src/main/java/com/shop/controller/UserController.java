package com.shop.controller;


import com.alibaba.fastjson.JSON;
import com.shop.common.utils.RedisClient;
import com.shop.feign.UserServiceRPC;
import com.shop.user.entity.User;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserServiceRPC userService;

    @PostMapping("/login")
    public String login(String username, String password, HttpServletResponse response){
        //验证用户密码
        User user = userService.login(username, password);
        if(user == null || "".equals(user.getUsername())){
            //验证错误!
            return "login";
        }
        //生成token
        String token = "" + ("SHOP-TOKEN:" + user.getUsername().hashCode()).hashCode();
        //把用户密码清除
        user.setPassword("");
        //user对象转为json串
        String userStr = JSON.toJSONString(user);
        //写入redis，设置半个小时清除
        RedisClient.setex(token,userStr,30 * 60);
        //设置到cookie中
        Cookie cookie = new Cookie("user-token",token);
        //设置cookie时间
        cookie.setMaxAge(30 * 60);
        //设置共享域
        cookie.setDomain(".shop.com");
        cookie.setPath("/");
        //添加cookie去response
        response.addCookie(cookie);

        return "success";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/token/{token}")
    @ResponseBody
    public String checkLogin(@PathVariable("token") String token,String callbk){

        String userStr = RedisClient.get(token);

        if("".equals(userStr) || userStr == null){
            //未登录,
            return "login";
        }

        RedisClient.setex(token,userStr,30 * 60 );

        if("".equals(callbk) || callbk == null){
            return userStr;
        }

        System.out.println("callbk :  " + callbk );

        return callbk + "(" + userStr + ")";

    }
}
