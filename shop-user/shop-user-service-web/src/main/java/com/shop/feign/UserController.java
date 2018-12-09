package com.shop.feign;

import com.shop.user.api.UserService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("SHOP-USER-SERVICE")
public interface UserController extends UserService{



}
