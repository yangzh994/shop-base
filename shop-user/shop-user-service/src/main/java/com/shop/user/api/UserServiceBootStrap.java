package com.shop.user.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceBootStrap {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceBootStrap.class,args);
    }
}
