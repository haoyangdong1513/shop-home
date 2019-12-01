package com.fh.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpclientUtils {

    //不变的是声明http的客户端
    private static CloseableHttpClient httpClient;

    static {
        //创建httpclient，代码只执行一次。减少客户端创建的频率，节省服务器资源。
        //你是连接另一个服务器，连接超时设置。目的是不回因为接口调不通造成大量的线程挂起，最总造成堵塞，tomcat
        //直接崩溃。
        //setConnectionRequestTimeout:设置与服务器连接的超时时间
        //setSocketTimeout:设置访问接口的超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setSocketTimeout(15000).build();
        //创建Http请求的客户端
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

    /**
     * 封装的post请求方法
     *
     * @param url
     * @param parameterMap
     * @return
     */
    public static String doPost(String url, Map<String, String> parameterMap) {
        //声明http请求的方式
        HttpPost httpPost = new HttpPost(url);
        //处理参数
        if (parameterMap != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            parameterMap.entrySet().forEach(entry -> {
                String key = entry.getKey();
                String value = entry.getValue();
                list.add(new BasicNameValuePair(key, value));
            });
            try {
                //处理参数
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
                //将参数加入到http请求中
                httpPost.setEntity(urlEncodedFormEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        //执行请求
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity,"UTF-8");
        } catch (HttpHostConnectException e) {
            e.printStackTrace();
            //服务器连接超时异常
            return JSON.toJSONString(ResponseServer.error(ServerEnum.SERVICE_CONNECTION_EXCEPTION));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            return JSON.toJSONString(ResponseServer.error(ServerEnum.SERVICE_CONNECTION_OVERTIME));
        } catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString(ResponseServer.error(ServerEnum.SERVICE_CONNECTION_UNKNOWN));
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



    /**
     *功能描述 (发送get请求带参数)
     * @author qqg
     * @date
     * @param  * @param null
     * @return
     */
    public static String doGet(String url, Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String  doDelete(String url){
        HttpDelete httpDelete = new HttpDelete(url);
        CloseableHttpResponse response = null;
        try {
            response  = httpClient.execute(httpDelete);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity,"UTF-8");
        } catch (HttpHostConnectException e) {
            e.printStackTrace();
            //服务器连接超时异常
            return JSON.toJSONString(ResponseServer.error(ServerEnum.SERVICE_CONNECTION_EXCEPTION));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            return JSON.toJSONString(ResponseServer.error(ServerEnum.SERVICE_CONNECTION_OVERTIME));
        }catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString(ResponseServer.error(ServerEnum.SERVICE_CONNECTION_UNKNOWN));
        }finally {
                if(response!=null){
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }


    public static  String doPut(String url ,Map<String,String> map){
        HttpPut httpPut = new HttpPut(url);
        if(map != null){
            List<NameValuePair> list  = new ArrayList<>();
            map.entrySet().forEach(entry -> {
                String key = entry.getKey();
                String value = entry.getValue();
                list.add(new BasicNameValuePair(key,value));
            });
            try {
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list,"UTF-8");
                httpPut.setEntity(urlEncodedFormEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        CloseableHttpResponse execute = null;
        try {
            execute= httpClient.execute(httpPut);
            HttpEntity entity = execute.getEntity();
            return EntityUtils.toString(entity,"UTF-8");
        } catch (HttpHostConnectException e) {
            e.printStackTrace();
            //服务器连接超时异常
            return JSON.toJSONString(ResponseServer.error(ServerEnum.SERVICE_CONNECTION_EXCEPTION));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            return JSON.toJSONString(ResponseServer.error(ServerEnum.SERVICE_CONNECTION_OVERTIME));
        } catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString(ResponseServer.error(ServerEnum.SERVICE_CONNECTION_UNKNOWN));
        }finally {
            if(execute != null){
                try {
                    execute.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
