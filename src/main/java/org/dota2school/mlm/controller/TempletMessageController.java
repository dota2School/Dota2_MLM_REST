package org.dota2school.mlm.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dota2school.mlm.frame.AppConfig;
import org.dota2school.mlm.util.G;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/mlm")
public class TempletMessageController {

    @Autowired
    private AppConfig appConfig;

    @RequestMapping(value = "/templet",method = RequestMethod.POST)
    public String templetMessage(String session,String formid)throws Exception{
        try(CloseableHttpClient closeableHttpClient = HttpClients.createDefault()){

            HttpUriRequest request = RequestBuilder.post("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send")
                    .addParameter("touser",session)
                    .addParameter("template_id","WAd_ueIx7sPx91AeAYV1afL3k5rnzTidrCHZc3iSxio")
                    .addParameter("form_id",formid)
                    .addParameter("access_token",getToken())
                    .addParameter("data","{\n" +
                            "      \"keyword1\": {\n" +
                            "          \"value\": \"339208499\", \n" +
                            "          \"color\": \"#173177\"\n" +
                            "      }, \n" +
                            "      \"keyword2\": {\n" +
                            "          \"value\": \"2015年01月05日 12:30\", \n" +
                            "          \"color\": \"#173177\"\n" +
                            "      }, \n" +
                            "      \"keyword3\": {\n" +
                            "          \"value\": \"粤海喜来登酒店\", \n" +
                            "          \"color\": \"#173177\"\n" +
                            "      } , \n" +
                            "      \"keyword4\": {\n" +
                            "          \"value\": \"广州市天河区天河路208号\", \n" +
                            "          \"color\": \"#173177\"\n" +
                            "      } \n" +
                            "  }").build();
            try(CloseableHttpResponse response = closeableHttpClient.execute(request)){
                return EntityUtils.toString(response.getEntity());
            }
        }
    }



    private String getToken()throws Exception{
        try(CloseableHttpClient closeableHttpClient = HttpClients.createDefault()){
            HttpUriRequest request = RequestBuilder
                    .get("https://api.weixin.qq.com/cgi-bin/token")
                    .addParameter("grant_type","client_credential")
                    .addParameter("appid",appConfig.getId())
                    .addParameter("secret",appConfig.getSecret())
                    .build();
            try(CloseableHttpResponse response = closeableHttpClient.execute(request)){
                Map<String,Object> result = G.G.fromJson(EntityUtils.toString(response.getEntity()),Map.class);
                if(result.get("access_token")!=null){
                    return result.get("access_token").toString();
                }else{
                    throw new IOException();
                }
            }
        }
    }

}
