package com.example.demo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: xiaozhu13539
 * @Date: 2021/3/4
 */
public class ThreadUtil {
    public  static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws Exception{

        Future<String> stringFuture = submitFuture(() -> {
            return getPhoneNumber();
        });
        System.out.println(stringFuture.get());


        //executorService.shutdown();
    }

    public static String getPhoneNumber(){
        return "18072504842";
    }

    public static <T> Future<T> submitFuture(Callable<T> task) {
        return executorService.submit(task);
    }
}
