package com.shop.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * prop配置加载类
 * yangzh
 * 2018-12-08
 */
public class LoadPropUtil {

    public static Properties load(String path) throws IOException {
        Properties prop = new Properties();
        InputStream in = LoadPropUtil.class.getClassLoader().getResourceAsStream("redis-conf.properties");
        prop.load(in);
        return prop;
    }

    public static void main(String[] args) throws IOException {
        load("redis-conf.properties");
    }
}
