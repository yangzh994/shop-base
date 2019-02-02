package com.shop.consumer;

import com.shop.common.utils.RedisClient;
import com.shop.sms.SendSms;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class RegisterQueueListener {

    @Autowired
    private SendSms sendSms;

    @JmsListener(destination = "${sms.register}", containerFactory = "jmsListenerContainerQueue")
    @Async
    public void receive(String text){
       // System.out.println(Thread.currentThread().getName() + "QueueListener: consumer-a 收到一条信息: " + text);
        try{
            if(!StringUtils.isEmpty(text)){
                String[] strs = text.split("#");
                String code = strs[0];
                String mobile = strs[1];
                String result = "success";//
               // String result = sendSms.sendRegisterChannel(code, mobile);
                if(result.contains("success")){
                    //发送短信成功(验证码,写入验证码服务器) key是电话号码,value是值 设置60秒后过期
                    RedisClient.setex(mobile,code,60);
                }else {
                    //发送短信失败(失败记录写入存储日志)
                }
            }
        }catch (Exception e){
            //TODO 处理异常
        }

    }
}
