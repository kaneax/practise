package com.example.demo.elasticsearch;

/**
 * @Author: xiaozhu13539
 * @Date: 2021/2/23
 */
public class WeixinTest {

    public static void main(String[] args) {
        String url = "https://m.hellobike.com/AppLivingNewH5/fat/latest/index.html#/detail?skuId=839176868132290560172431528&storeId=172000&userNewId=1200198554&notencode=true&from=qrcode";

        String urlResult = url.replace("from=qrcode", "from=nowxqrcode ");
        System.out.println(urlResult);


    }
}
