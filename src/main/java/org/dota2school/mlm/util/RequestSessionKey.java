package org.dota2school.mlm.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dota2school.mlm.frame.AppConfig;
import org.dota2school.mlm.model.RequestSessionKeyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 请求session key
 * @author xujq
 * @time 2017-7-10
 */
@Component
public class RequestSessionKey {
    public static final Logger LOG = LoggerFactory.getLogger(RequestSessionKey.class);

    @Autowired
    private AppConfig appConfig;

    private CloseableHttpClient client = HttpClients.createDefault();



    public RequestSessionKeyResponse request(String code)throws IOException{
        HttpUriRequest request = RequestBuilder.get("https://api.weixin.qq.com/sns/jscode2session")
                .addParameter("grant_type","authorization_code")
                .addParameter("js_code",code)
                .addParameter("secret",appConfig.getSecret())
                .addParameter("appid",appConfig.getId()).build();
        try(CloseableHttpResponse response = client.execute(request)){
            String resposneString  = EntityUtils.toString(response.getEntity());
            LOG.info("Request session key {} ",resposneString);
            return G.G.fromJson(resposneString,RequestSessionKeyResponse.class);
        }
    }

}
