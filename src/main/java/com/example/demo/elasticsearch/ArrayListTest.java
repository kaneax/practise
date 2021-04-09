package com.example.demo.elasticsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiaozhu13539
 * @Date: 2021/3/29
 */
public class ArrayListTest {

    public static void main(String[] args) {

        //grafana

        List<Integer> integers = new ArrayList<>(5);
        integers.add(1);
        integers.add(2);
        integers.add(2);
        integers.add(4);
        integers.add(5);

        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i)%2==0){
                integers.remove(i);
                //i--;
            }
        }

        System.out.println(integers);

    }
}
