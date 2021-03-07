package com.example.demo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: xiaozhu13539
 * @Date: 2021/3/7
 */
public class ThreadPools {

    /**
     * 静态内部类来创建单例
     */
    private static class ThreadPoolManagerHolder{
        private static ThreadPools instance = new ThreadPools();
    }

    public static ThreadPools getInstance(){
        return ThreadPoolManagerHolder.instance;
    }
    ExecutorService goodsExecutorService = null;

    /**
     * 私有空参构造函数
     */
    private ThreadPools(){

         goodsExecutorService = Executors.newFixedThreadPool(10);
    }

    /**
     * 没有返回值
     * 执行线程
     * @param task
     */
    public void refreshGoods(Runnable task){
        goodsExecutorService.submit(task);
    }

    /**
     * 有返回值
     * @param task
     * @param <T>
     * @return
     */
    public <T> Future<T> getGoodsList(Callable<T> task){
       return goodsExecutorService.submit(task);
    }

}
