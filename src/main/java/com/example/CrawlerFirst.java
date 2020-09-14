package com.example;


import com.sun.javafx.fxml.builder.URLBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URISyntaxException;

/**
 * description: CrawlerFirst <br>
 * author: ygj <br>
 * version: 1.0 <br>
 */
public class CrawlerFirst {

    public static void main(String[] args) {
        //相当于打开了浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();

        CloseableHttpResponse response = null;
        try {
            //输入网址 http://www.netbian.com/e/search/result/?searchid=80
            URIBuilder uriBuilder = new URIBuilder("http://www.netbian.com/e/search/result");
            uriBuilder.addParameter("searchid","80");

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            //System.out.println("发起请求的信息" + httpGet);
            //访问网站
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String entityString = EntityUtils.toString(entity,"gbk");
                System.out.println(entityString.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            if (response!=null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
