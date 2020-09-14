package com.example;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * description: CrawlerFirst <br>
 * author: ygj <br>
 * version: 1.0 <br>
 */
public class CrawlerPostParam {

    public static void main(String[] args) {
        //相当于打开了浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();

        CloseableHttpResponse response = null;
        try {
            //输入网址 http://www.netbian.com/e/search/result/?searchid=80
            URIBuilder uriBuilder = new URIBuilder("http://www.netbian.com/e/search/result");

            //创建参数的集合
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            BasicNameValuePair basicNameValuePair = new BasicNameValuePair("searchid","80");
            params.add(basicNameValuePair);
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf8");


            HttpPost httpPost = new HttpPost(uriBuilder.build());
            //System.out.println("发起请求的信息" + httpGet);
            httpPost.setEntity(formEntity);
            //访问网站
            response = httpClient.execute(httpPost);
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
