package com.example.demo.elasticsearch;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.Date;
import java.util.HashMap;

/**
 * @Author: xiaozhu13539
 * @Date: 2021/2/20
 */
public class IndexApi {

    public void  getIndexRequest(){

        IndexRequest request = new IndexRequest("posts");
        request.id("1");

        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";

        request.source(jsonString, XContentType.JSON);


        HashMap<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user","kimchy");
        jsonMap.put("postDate",new Date());
        jsonMap.put("message","trying out elasticsearch");

        IndexRequest posts = new IndexRequest("posts").id("1").source(jsonMap);

    }

    public static void main(String[] args) {
        int n = 16;
        int sc = 0 ;
        sc = n - (n >>> 2);

        System.out.println(sc);
    }
}
