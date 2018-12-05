package com.shop.controller;

import com.shop.user.api.UserService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("SHOP-USER-SERVICE")
public interface UserController extends UserService{

}
