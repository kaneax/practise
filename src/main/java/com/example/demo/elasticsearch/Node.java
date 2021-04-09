package com.example.demo.elasticsearch;

/**
 * @Author: xiaozhu13539
 * @Date: 2021/4/8
 */
public class Node {

    static final Node SHARED = new Node();
    //独家
    static final Node EXCLUSIVE = null;
    //取消
    static final int CANCELLED =  1;
    //信号
    static final int SIGNAL    = -1;
    //propagate
    static final int PROPAGATE = -3;
    //等待状态
    volatile int waitStatus;
    //下一个
    volatile Node next;
    //前置
    volatile Node prev;
    //线程
    volatile Thread thread;
    //下一个节点
    Node nextWaiter;
}
