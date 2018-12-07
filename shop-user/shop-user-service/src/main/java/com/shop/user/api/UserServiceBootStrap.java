package com.shop.user.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.shop.user.api.mapper")
public class UserServiceBootStrap {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceBootStrap.class,args);
    }
}
