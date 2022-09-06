package com.example.rank.wx;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @ClassName wxTest
 * @Description
 * @Author ts
 * @Date 2022/8/8 10:45
 * @Version 1.0
 */
public class wxTest {
    public final static String APP_ID = "wxf1b8f62a14bb6983";


    public static void main(String[] args) {
        getUserOpenIdByCode("001c6DFa1Zj0JD0s6VHa1m9zk71c6DF0");
    }

    public static String getUserOpenIdByCode(String code){



        //String appSecret = wxCodeProperties.getConfig().getAppSecert();
        String appSecret = "23fae468dea7df2ceebf3017e0609591";
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
                + "appid="+APP_ID
                +"&secret="+appSecret
                +"&code="+code
                +"&grant_type=authorization_code";
        System.out.println(url);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpget = new HttpGet(
                url);
        CloseableHttpResponse execute = null;
        try {
            try{
                execute = httpClient.execute(httpget);
                HttpEntity entity = execute.getEntity();
                Header[] allHeaders = execute.getAllHeaders();
                if (entity.getContent()!=null){
                    String openId = EntityUtils.toString(entity, Charset.forName("UTF-8"));
                    JSONObject jsonObject = JSONObject.parseObject(openId);
                    System.out.println(openId);
                    System.out.println(jsonObject.get("openid"));
                    StatusLine statusLine = execute.getStatusLine();
                    return openId;
                }
            }finally {
                execute.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
