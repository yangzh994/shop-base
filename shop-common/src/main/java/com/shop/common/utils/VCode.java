package com.shop.common.utils;

import java.util.Random;

/**
 * 验证码生成类
 * yangzh
 */
public class VCode {


    public static String number4Code(){
        return numberCode(4);
    }

    public static String number6Code(){
        return numberCode(6);
    }

    private static String numberCode(int num){
        Random random = new Random();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < num ; i++) {
            int number = random.nextInt(10);
            buffer.append(number);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        String code = number6Code();
        System.out.println(code);
    }
}
