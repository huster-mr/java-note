package com.example.spring.learn.http;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author
 * @Description
 * @Date 2021/12/22
 */
public class HttpInvokeClient {
    private static final Logger logger = LoggerFactory.getLogger(HttpInvokeClient.class);

    private static final Integer DEFAULT_CONNECT_TIMEOUT = 5000;

    private static final CloseableHttpClient HTTP_CLIENT = HttpClientBuilder.create()
            .setDefaultRequestConfig(RequestConfig.custom()
            .setConnectTimeout(5000).setSocketTimeout(10000).setConnectionRequestTimeout(10000).build())
            .setConnectionTimeToLive(15, TimeUnit.MINUTES)
            .disableCookieManagement()
            .setMaxConnTotal(1000)
            .setMaxConnPerRoute(50)
            .build();

    public static String get(String url, Integer timeoutMs) throws Exception {
        HttpGet method = new HttpGet(url);
        if (timeoutMs != null) {
            method.setConfig(buildTimeOutConfig(timeoutMs));
        }
        final CloseableHttpResponse response = HTTP_CLIENT.execute(method);
        final String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return responseBody;
        } else {
            logger.error("http get error, url:{}, returnCode:{}, response:{}", url, response.getStatusLine(), responseBody);
            return null;

        }

    }

    private static RequestConfig buildTimeOutConfig(int timeout){
        return RequestConfig.custom().setConnectTimeout(DEFAULT_CONNECT_TIMEOUT).setSocketTimeout(timeout).build();
    }

    // 固定线程池
    private static final ExecutorService FIXED_THREAD_POOL = new ThreadPoolExecutor(20, 20,
            0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
            new ThreadFactory() {
                private AtomicInteger index = new AtomicInteger(0);
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "FIXED_THREAD_POOL_" + index.incrementAndGet());
                }
            });

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 2; i++) {
            final String url = "http://www.baidu.com";
            FIXED_THREAD_POOL.execute(() -> {
                String res = null;
                try {
                    res = HttpInvokeClient.get(url, 5000);
                } catch (Exception e){

                }
                if (res != null) {
                    System.out.println(url);
                    System.out.println(res);
                }
            });

        }
    }
}
