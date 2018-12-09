package com.shop.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class RegisterQueueListener {

    @JmsListener(destination = "${sms.register}", containerFactory = "jmsListenerContainerQueue")
    @Async
    public void receive(String text){
        System.out.println(Thread.currentThread().getName() + "QueueListener: consumer-a 收到一条信息: " + text);
    }
}
