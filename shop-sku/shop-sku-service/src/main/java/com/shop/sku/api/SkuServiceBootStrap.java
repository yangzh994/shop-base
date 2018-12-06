package com.shop.sku.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SkuServiceBootStrap {

    public static void main(String[] args) {
        SpringApplication.run(SkuServiceBootStrap.class,args);
    }
}
