package com.example;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * description: HttpClientPoolTest <br>
 * author: ygj <br>
 * version: 1.0 <br>
 */
public class HttpClientPoolTest {


    public static void main(String[] args) {
        //创建连接池管理器
        PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager();


        //设置最大连接数
        pool.setMaxTotal(100);

        //设置每个主机的最大连接数
        pool.setDefaultMaxPerRoute(10);

        //使用连接池管理器发起请求
        doGet(pool);
    }

    private static void doGet(PoolingHttpClientConnectionManager pool) {
        //是不每次创建HttpClient 而是从连接池获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(pool).build();

        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String entityString = EntityUtils.toString(entity, "utf8");
                System.out.println(entityString.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response!= null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
