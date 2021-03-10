package com.example.demo.solution;

import java.util.UUID;

/**
 * @Author: xiaozhu13539
 * @Date: 2021/3/3
 * 动态规划
 */
public class Solution1 {
    public static void main(String[] args) {

        String uuid = UUID.randomUUID().toString().replace("-", "");

        System.out.println(uuid.substring(0,30));


        System.out.println(uuid.length());
    }
}
