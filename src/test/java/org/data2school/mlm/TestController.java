package org.data2school.mlm;

import com.google.gson.Gson;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.dota2school.mlm.model.BindRequest;
import org.dota2school.mlm.model.QuerySignRequest;
import org.dota2school.mlm.model.RegisterRequest;
import org.dota2school.mlm.model.ResponseSignRequest;
import org.dota2school.mlm.util.G;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by nt on 2017/7/14.
 */
public class TestController {


    /**
     * 创建SSL安全连接
     *
     * @return
     */
    public  static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                @Override
                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                @Override
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }

    @Test
    public void testBind()throws Exception{
        String s="Tom喵🐱Snow";
        try(CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).build()){
            BindRequest request = new BindRequest();
            request.setSession("oUtn60MyXkjGjhLGD1PL-KaaXmqg");
            request.setRoleType("1");
            request.setNikeName(s);
            //HttpPost post = new HttpPost("https://wxapi.fishiny.com/api/mlm/bind");
            HttpPost post = new HttpPost("https://localhost:444/api/mlm/bind");
            post.setEntity(new StringEntity(G.G.toJson(request)));
            try(CloseableHttpResponse response = client.execute(post)){
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        }
    }

    @Test
    public void createSign()throws Exception{
        try(CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).build()){
            HttpPost post = new HttpPost("https://47.92.82.249/api/mlm/create_sign");
            StringEntity entity = new StringEntity("{\"session\":\"oUtn60MfarHE-4mGKcbMKwqh0srE\",\"classType\":\"初级班\",\"teachTime\":\"半小时\",\"teachDate\":\"2017-7-14 00:00:00\",\"students\":[\"张三\",\"李四\"]}", Charset.forName("utf-8"));
            post.setEntity(entity);
            try(CloseableHttpResponse res = client.execute(post)){
                System.out.println(EntityUtils.toString(res.getEntity()));
            }
        }
    }

    @Test
    public void testP(){
        String s = "半小时 ";
        System.out.println(Double.parseDouble(s.replace("半","0.5").replace("小时","")));
    }

    @Test
    public void test()throws Exception{
        try(CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).build()){
            HttpPost post = new HttpPost("https://localhost:80/api/mlm/register");
            RegisterRequest registerRequest =new RegisterRequest();
            post.setEntity(new StringEntity(new Gson().toJson(registerRequest),Charset.defaultCharset()));
            try(CloseableHttpResponse response = client.execute(post)){
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        }
    }

    @Test
    public void responseSign()throws Exception{
        Gson G = new Gson();
        try(CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).build()){
            HttpPost post = new HttpPost("https://wxapi.fishiny.com:8100/api/mlm/response_sign");
            ResponseSignRequest responseSignRequest = new ResponseSignRequest();
            responseSignRequest.setSignId("3");
            responseSignRequest.setStatus("满意");
            responseSignRequest.setEvaluate("Hahahahahaha");
            responseSignRequest.setSession("oUtn60MfarHE-4mGKcbMKwqh0srE");
            post.setEntity(new StringEntity(G.toJson(responseSignRequest),Charset.forName("utf-8")));
            System.out.println(G.toJson(responseSignRequest));
            try(CloseableHttpResponse response = client.execute(post)){
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        }
    }

    @Test
    public void querySign()throws Exception{
        Gson G = new Gson();
        HttpGet get = new HttpGet("http://zq.win007.com/cn/SubLeague/10.html");
        try(CloseableHttpClient client  = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).build()){
//            HttpPost post = new HttpPost("https://wxapi.fishiny.com/api/mlm/query_sign");
//            QuerySignRequest querySignRequest = new QuerySignRequest();
//            querySignRequest.setSession("oUtn60MfarHE-4mGKcbMKwqh0srE");
//            querySignRequest.setSignId("3");
//            System.out.println(G.toJson(querySignRequest));
//            post.setEntity(new StringEntity(G.toJson(querySignRequest),Charset.forName("utf-8")));
//            try(CloseableHttpResponse response = client.execute(post)){
//                System.out.println(EntityUtils.toString(response.getEntity()));
//            }
            try(CloseableHttpResponse response = client.execute(get)){

                System.out.println(EntityUtils.toString(response.getEntity()));

            }
        }
    }


    @Test
    public void queryUser()throws Exception{
        Gson G = new Gson();
        try(CloseableHttpClient client  = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).build()){
            HttpPost post =  new HttpPost("https://localhost:443/api/mlm/query_user")
                    ;
            QuerySignRequest querySignRequest = new QuerySignRequest();
            querySignRequest.setSession("oUtn60MfarHE-4mGKcKwqh0srE");
            querySignRequest.setSignId("3");
            System.out.println(G.toJson(querySignRequest));
            post.setEntity(new StringEntity(G.toJson(querySignRequest),Charset.forName("utf-8")));
            try(CloseableHttpResponse response = client.execute(post)){
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        }
    }

    @Test
    public void querySignById()throws Exception{
        Gson G = new Gson();
        try(CloseableHttpClient client  = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).build()){
            HttpPost post =  new HttpPost("https://localhost:443/api/mlm/query_sign_by_id")
                  ;
            QuerySignRequest querySignRequest = new QuerySignRequest();
            querySignRequest.setSession("oUtn60MfarHE-4mGKcbMKwqh0srE");
            querySignRequest.setSignId("3");
            System.out.println(G.toJson(querySignRequest));
            post.setEntity(new StringEntity(G.toJson(querySignRequest),Charset.forName("utf-8")));
            try(CloseableHttpResponse response = client.execute(post)){
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        }
    }


    @Test
    public void queryScore()throws Exception{
        Gson G = new Gson();
        try(CloseableHttpClient client  = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).build()){
            HttpPost post =  new HttpPost("https://wxapi.fishiny.com/api/mlm/score");
            QuerySignRequest querySignRequest = new QuerySignRequest();
            querySignRequest.setSession("oUtn60MfarHE-4mGKcbMKwqh0srE");
            //querySignRequest.setSignId("3");
            System.out.println(G.toJson(querySignRequest));
            post.setEntity(new StringEntity(G.toJson(querySignRequest),Charset.forName("utf-8")));
            try(CloseableHttpResponse response = client.execute(post)){
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        }
    }

}
