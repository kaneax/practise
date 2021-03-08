package com.example.demo.common;

import java.net.InetAddress;

/**
 * @Author: xiaozhu13539
 * @Date: 2021/3/8
 * 环境参数
 */
public class Environment {
    public static String IP;
    public static String APP_ID;
    public static String ENV;

    public Environment(){

    }

    static {
        try {
            APP_ID = System.getProperty("APPID");
            ENV = System.getProperty("env");
            IP = InetAddress.getLocalHost().getHostAddress();
        } catch (Throwable var1) {
            ;
        }
    }
}
