package com.shop.feign;

import com.shop.user.api.UserService;
import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient("SHOP-USER-SERVICE")
public interface UserServiceRPC extends UserService{

}
