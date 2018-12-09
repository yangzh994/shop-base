package com.shop;

import com.shop.sms.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmsBootStrap {

    public static void main(String[] args) {
        SpringApplication.run(SmsBootStrap.class,args);
    }
}
