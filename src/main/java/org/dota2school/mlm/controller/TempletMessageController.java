package org.dota2school.mlm.controller;

import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dota2school.mlm.frame.AppConfig;
import org.dota2school.mlm.util.G;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/mlm")
public class TempletMessageController {
    public static final Logger LOG  = LoggerFactory.getLogger(TempletMessageController.class);

    @Autowired
    private AppConfig appConfig;

    @RequestMapping(value = "/templet",method = RequestMethod.POST)
    public String templetMessage(@RequestBody String data)throws Exception{
       LOG.info("Recive data:",data);
       Map<String,String> d = G.G.fromJson(data,Map.class);
       Map<String,Object> dd = new HashMap<>();
       dd.put("touser",d.get("session"));
       dd.put("template_id","WAd_ueIx7sPx91AeAYV1afL3k5rnzTidrCHZc3iSxio");
       dd.put("form_id",d.get("formid"));
       try(CloseableHttpClient closeableHttpClient = HttpClients.createDefault()){
            HttpPost request = new HttpPost("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+getToken());
            request.setEntity(new StringEntity(G.G.toJson(dd)));
            try(CloseableHttpResponse response = closeableHttpClient.execute(request)){
                String result =  EntityUtils.toString(response.getEntity());
                LOG.info("Result: {}",result);
                return result;
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
                Map<String,Object> result = G.G.fromJson(EntityUtils.toString(response.getEntity()),HashMap.class);
                if(result.get("access_token")!=null){
                    LOG.info("Request tocken {}",G.G.toJson(result));
                    return result.get("access_token").toString();
                }else{
                    throw new IOException();
                }
            }
        }
    }

}
