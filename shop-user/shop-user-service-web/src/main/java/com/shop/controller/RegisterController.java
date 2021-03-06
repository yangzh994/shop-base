package com.shop.controller;

import com.shop.common.utils.VCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.jms.Queue;

@Controller
public class RegisterController {

    @Resource(name = "registerQueue")
    private Queue registerQueue;

    @Autowired
    private JmsMessagingTemplate jms;

    @GetMapping("/register/sendCode/{phone}")
    public void registerSendCode(@PathVariable("phone") String phone){
        String code = VCode.number6Code();
        String msg = code + "#" + phone;
        jms.convertAndSend(registerQueue,msg);
    }

    @GetMapping("/info")
    public String info(){
        return "info";
    }
}
