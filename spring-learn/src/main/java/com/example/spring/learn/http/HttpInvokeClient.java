package com.example.spring.learn.http;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
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
                    .setConnectTimeout(5000)
                    .setSocketTimeout(10000)
                    .setConnectionRequestTimeout(10000)
                    .build())
            .setConnectionTimeToLive(15, TimeUnit.MINUTES)
            .disableCookieManagement()
            .setMaxConnTotal(1000)
            .setMaxConnPerRoute(50)
            .build();

    public static String get(String url, Integer timeoutMs) {
        HttpGet method = new HttpGet(url);
        if (timeoutMs != null) {
            method.setConfig(buildTimeOutConfig(timeoutMs));
        }
        try {
            final CloseableHttpResponse response = HTTP_CLIENT.execute(method);
            final String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                logger.info("HttpInvokeClient get execute success, url:{}, result:{}", url, responseBody);
                return responseBody;
            } else {
                logger.info("HttpInvokeClient get failed, url:{}, returnCode:{}, response:{}", url, response.getStatusLine(), responseBody);
            }
        } catch (Exception e) {
            logger.error("HttpInvokeClient get error, url:" + url, e);
        }
        return null;
    }

    public static String post(String url, Map<String, String> paramMap, Integer timeoutMs) {
        HttpPost method = new HttpPost(url);
        if (timeoutMs != null) {
            method.setConfig(buildTimeOutConfig(timeoutMs));
        }
        if (paramMap != null && !paramMap.isEmpty()) {
            List<NameValuePair> formItem = Lists.newArrayList();
            for(String key : paramMap.keySet()) {
                if (paramMap.get(key) != null) {
                    formItem.add(new BasicNameValuePair(key, paramMap.get(key)));
                }
            }
            final UrlEncodedFormEntity form = new UrlEncodedFormEntity(formItem, Charsets.UTF_8);
            method.setEntity(form);
        }
        try {
            final CloseableHttpResponse response = HTTP_CLIENT.execute(method);
            final String responseBody = EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                logger.info("HttpInvokeClient post success, url:{}, result:{}", url, response);
                return responseBody;
            } else {
                logger.info("HttpInvokeClient post failed, url:{}, param:{}, resultCode:{}, response:{}", url, paramMap, response.getStatusLine().getStatusCode(), responseBody);
            }
        } catch (Exception e) {
            logger.error("HttpInvokeClient post error, url:" + url, e);
        }
        return null;
    }

    public static String postWithJsonParam(String url, String jsonParam, Integer timeoutMs) {
        HttpPost method = new HttpPost(url);
        if (timeoutMs != null) {
            method.setConfig(buildTimeOutConfig(timeoutMs));
        }
        if (StringUtils.isNotBlank(jsonParam)) {
            method.setHeader("Content-Type", "application/json; charset=UTF-8");
            method.setEntity(new StringEntity(jsonParam, ContentType.APPLICATION_JSON));
        }
        try {
            final CloseableHttpResponse response = HTTP_CLIENT.execute(method);
            final String responseBody = EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                logger.info("HttpInvokeClient post success, url:{}, result:{}", url, response);
                return responseBody;
            } else {
                logger.info("HttpInvokeClient post failed, url:{}, param:{}, resultCode:{}, response:{}", url, jsonParam, response.getStatusLine().getStatusCode(), responseBody);
            }
        } catch (Exception e) {
            logger.error("HttpInvokeClient post error, url:" + url, e);
        }
        return null;
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

//    public static void main(String[] args) throws Exception {
//        for (int i = 0; i < 100; i++) {
//            FIXED_THREAD_POOL.execute(() -> {
//                String res = null;
//                try {
//                    res = HttpInvokeClient.get(url, 5000);
//                } catch (Exception e){
//
//                }
//                if (res != null) {
//                    System.out.println(url);
//                    System.out.println(res);
//                }
//            });
//
//        }
//    }
}
