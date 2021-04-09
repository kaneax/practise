package com.example.demo.elasticsearch;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: xiaozhu13539
 * @Date: 2021/2/23
 */
public class WeixinTest {

    public static void main(String[] args) {
        /*String url = "https://m.hellobike.com/AppLivingNewH5/fat/latest/index.html#/detail?skuId=839176868132290560172431528&storeId=172000&userNewId=1200198554&notencode=true&from=qrcode";

        String urlResult = url.replace("from=qrcode", "from=nowxqrcode ");
        System.out.println(urlResult);*/

        //Date date = new Date();
        //DateUtils
        //System.out.println(date.toString());

        //getOrder();

        String ss = "0";

        boolean numeric = StringUtils.isNumeric(ss);

       // StringUtils.isNumeric()

        System.out.println(numeric);


        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("1");
        strings.add("1");
        strings.add("1");
        for (int i = 0; i < strings.size(); i++){
            String result = strings.get(i);
            if (result.equals("1")){
                strings.remove(i);
                i--;
            }
        }

        System.out.println(strings.size());



    }


    public static void getOrder(){
        String people ;
        if (true){
            people = "1";
        }else {
            people = "2";
        }
        System.out.println(people);
    }
}
