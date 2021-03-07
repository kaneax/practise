package com.example.demo.thread;

import java.util.UUID;

/**
 * @Author: xiaozhu13539
 * @Date: 2021/3/2
 */
public class Context {

    private static final ThreadLocal<String> REQUEST_LOCAL = new ThreadLocal<String>();

    public  static void setRequestId(){
        //if (REQUEST_LOCAL.get() == null){

        //}

        REQUEST_LOCAL.set(UUID.randomUUID().toString().replace("-",""));
    }

    public static String getRequestId(){
        return REQUEST_LOCAL.get();
    }


    public static void main(String[] args) {

    }
}
