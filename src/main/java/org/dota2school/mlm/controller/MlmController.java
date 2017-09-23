package org.dota2school.mlm.controller;

import com.google.gson.Gson;
import org.dota2school.mlm.domain.*;
import org.dota2school.mlm.frame.AppConfig;
import org.dota2school.mlm.model.*;
import org.dota2school.mlm.respository.SignRespository;
import org.dota2school.mlm.respository.SignStudentRespository;
import org.dota2school.mlm.respository.TeacherCountRespository;
import org.dota2school.mlm.respository.UserRespository;
import org.dota2school.mlm.util.AES;
import org.dota2school.mlm.util.RequestSessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author xujq
 * @time 2017-7-10
 */
@RestController
@RequestMapping("/api/mlm")
public class MlmController {

    public static final Logger LOG = LoggerFactory.getLogger(MlmController.class);

    public static final ThreadLocal<DateFormat> formate = ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private SignRespository signRespository;

    @Autowired
    private SignStudentRespository signStudentRespository;

    @Autowired
    private RequestSessionKey requestSessionKey;

    @Autowired
    private TeacherCountRespository teacherCountRespository;

    private Gson G = new Gson();

    /**
     * {country=China, watermark={timestamp=1.504056528E9, appid=wxc210c7072c5517db}, gender=1.0, province=Hebei, city=Shijiazhuang, avatarUrl=https://wx.qlogo.cn/mmopen/ajNVdqHZLLBJYvicxteywOuHd72VC2vsg5lV9p3zfJMQh9dicopO2KAHZ9gF1XQQt6ZkgWgRdy99cBlboaUR9w5w/0, openId=oUtn60JwL9RnyQ-8Er2fl5dqolv0, nickName=骨感Jerry, language=zh_CN}

     * @param requestJson
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String updateNickName(@RequestBody String requestJson){
        RegisterResponse registerResponse = new RegisterResponse();
        LOG.info("Recive {}",requestJson);
        try{
            RegisterRequest registerRequest = G.fromJson(requestJson,RegisterRequest.class);
            RequestSessionKeyResponse requestSessionKeyResponse = requestSessionKey.request(registerRequest.getCode());
            AES aes = new AES(requestSessionKeyResponse.getSession_key());
            HashMap data = aes.decryptData(registerRequest.getEncryptedData(),registerRequest.getIv());
            User user = userRespository.findOne(requestSessionKeyResponse.getOpenid());
            if(user!=null){
                user.setAvatarUrl(data.get("avatarUrl").toString());
                user.setNickNameP(data.get("nickName").toString());
                user.setCountry(data.get("country").toString());
                user.setGender(data.get("gender").toString());
                user.setProvince(data.get("province").toString());
                user.setCity(data.get("city").toString());
                user.setAvatarUrl(data.get("avatarUrl").toString());
                user.setUpdateTime(new Date());
            }
            userRespository.save(user);
        }catch (Exception ex){
            LOG.warn("Failed ",ex);
            registerResponse.setMessage(ex.getMessage());
            registerResponse.setSession("-1");
            registerResponse.setSession("");
        }
        return G.toJson(registerResponse);
    }

    /**
     * 用户注册
     * @param requestJson 请求注册的json
     * @return 结果
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestBody String requestJson){
        RegisterResponse registerResponse = new RegisterResponse();
        LOG.info("Recive {}",requestJson);
        try{
            RegisterRequest registerRequest = G.fromJson(requestJson,RegisterRequest.class);
            RequestSessionKeyResponse requestSessionKeyResponse = requestSessionKey.request(registerRequest.getCode());
            AES aes = new AES(requestSessionKeyResponse.getSession_key());
            HashMap data = aes.decryptData(registerRequest.getEncryptedData(),registerRequest.getIv());
            User user = userRespository.findOne(requestSessionKeyResponse.getOpenid());
            LOG.info(data.toString());
            if(user == null) {
                user = new User();
                user.setAvatarUrl(data.get("avatarUrl").toString());
                user.setNickNameP(data.get("nickName").toString());
                user.setNickName(data.get("nickName").toString());
                user.setCountry(data.get("country").toString());
                user.setGender(data.get("gender").toString());
                user.setProvince(data.get("province").toString());
                user.setCity(data.get("city").toString());
                user.setOpenId(requestSessionKeyResponse.getOpenid());
                user.setUpdateTime(new Date());
                userRespository.save(user);
            }
            registerResponse.setMessage("success");
            registerResponse.setSession("1");
            registerResponse.setSession(requestSessionKeyResponse.getOpenid());
       }catch (Exception ex){
            LOG.warn("Failed ",ex);
            registerResponse.setMessage(ex.getMessage());
            registerResponse.setSession("-1");
            registerResponse.setSession("");
       }
       return G.toJson(registerResponse);
    }

    @RequestMapping(value ="/query_user",method = RequestMethod.POST)
    public String queryUser(@RequestBody String session){
        QueryUserRequest request = G.fromJson(session,QueryUserRequest.class);
        User user = userRespository.findOne(request.getSession());
        UserInfo userInfo = new UserInfo();
        if(user!=null){
            userInfo.setStreamId(user.getStreamId());
            userInfo.setRoleType(user.getType()+"");
            userInfo.setRankScore(user.getRankScore());
            userInfo.setClassType(user.getClassType());
            userInfo.setAvatarUrl(user.getAvatarUrl());
            userInfo.setContents(user.getClassContent());
            userInfo.setNickName(user.getNickName());
        }
        return G.toJson(userInfo);
    }

    @RequestMapping("/login")
    public String login(@RequestBody String requestJson){
        LOG.info("Recive {}",requestJson);
        LoginResponse registerResponse = new LoginResponse();
        try{
            LoginRequest loginRequest = G.fromJson(requestJson,LoginRequest.class);
            RequestSessionKeyResponse requestSessionKeyResponse = requestSessionKey.request(loginRequest.getCode());
            User user = userRespository.findOne(requestSessionKeyResponse.getOpenid());
            if(user == null) {
                registerResponse.setMessage("no user");
                registerResponse.setSession("1");
            }else{
                registerResponse.setSession(user.getOpenId());
                registerResponse.setMessage("have user");
            }
        }catch (Exception ex){
            registerResponse.setMessage(ex.getMessage());
            registerResponse.setSession("-1");
            LOG.warn("Failed ",ex);
        }
        return G.toJson(registerResponse);
    }


    @RequestMapping("/bind")
    public String bind(@RequestBody String requestJson){
        LOG.info("Recive {}",requestJson);
        BindRequest bindRequest   = G.fromJson(requestJson,BindRequest.class);
        BindResponse bindResponse = new BindResponse();
        User user = userRespository.findOne(bindRequest.getSession());
        user.setOpenId(bindRequest.getSession());
        user.setUpdateTime(new Date());
        user.setClassContent(bindRequest.getContents());
        user.setClassType(bindRequest.getClassType());
        user.setRankScore(bindRequest.getRankScore());
        user.setStreamId(bindRequest.getSteamID());
        user.setType(Integer.parseInt(bindRequest.getRoleType()));
        user.setNickName(bindRequest.getNikeName());
        user.setClassName(bindRequest.getClassName());
        userRespository.save(user);
        UserInfo userInfo = new UserInfo();
        userInfo.setContents(user.getClassContent());
        userInfo.setClassType(user.getClassType());
        userInfo.setNickName(user.getNickName());
        userInfo.setRankScore(user.getRankScore());
        userInfo.setRoleType(user.getType()+"");
        userInfo.setStreamId(user.getStreamId());
        userInfo.setClassName(user.getClassName());
        bindResponse.setUserInfo(userInfo);
        bindResponse.setMessage("success");
        bindResponse.setSuccess("success");
        return G.toJson(bindResponse);
    }


    @RequestMapping("/modify")
    public String modify(@RequestBody String requestJson){
        LOG.info("Recive {}",requestJson);
        BindRequest bindRequest   = G.fromJson(requestJson,BindRequest.class);
        BindResponse response = new BindResponse();
        User user = userRespository.findOne(bindRequest.getSession());
        user.setOpenId(bindRequest.getSession());
        user.setUpdateTime(new Date());
        user.setClassContent(bindRequest.getContents());
        user.setClassType(bindRequest.getClassType());
        user.setRankScore(bindRequest.getRankScore());
        user.setStreamId(bindRequest.getSteamID());
        user.setType(Integer.parseInt(bindRequest.getRoleType()));
        user.setNickName(bindRequest.getNikeName());
        user.setClassName(bindRequest.getClassName());
        userRespository.save(user);
        response.setMessage("success");
        response.setSuccess("success");
        UserInfo userInfo = new UserInfo();
        userInfo.setContents(user.getClassContent());
        userInfo.setClassType(user.getClassType());
        userInfo.setNickName(user.getNickName());
        userInfo.setRankScore(user.getRankScore());
        userInfo.setRoleType(user.getType()+"");
        userInfo.setStreamId(user.getStreamId());
        userInfo.setClassName(user.getClassName());
        response.setUserInfo(userInfo);
        return G.toJson(bindRequest);
    }


    @RequestMapping("/create_sign")
    public String createSign(@RequestBody String requestJson){
        LOG.info("Recive {}",requestJson);
        CreateSignRequest createSignRequest = G.fromJson(requestJson,CreateSignRequest.class);
        CreateSignResponse response = new CreateSignResponse();
        try{
            Sign sign = new Sign();
            sign.setOpenId(createSignRequest.getSession());
            sign.setTeachDate(formate.get().parse(createSignRequest.getTeachDate()));
            sign.setTeachTime(createSignRequest.getTeachTime());
            sign.setUpdateTime(new Date());
            sign.setClassType(createSignRequest.getClassType());
            try{
                sign.setTeachTimeInt(Double.parseDouble(
                        createSignRequest.getTeachTime().
                                replace("半","0.5").
                                replace("小时","")));
            }catch (Exception ex){
                sign.setTeachTimeInt(0);
            }
            signRespository.save(sign);
            response.setSuccess("success");
            response.setMessage("success");
            PunchCard punchCard = new PunchCard();
            punchCard.setSignId(sign.getSign_id()+"");
            punchCard.setOpenId(sign.getOpenId());
            punchCard.setTeachTime(formate.get().format(sign.getTeachDate()));
            punchCard.setTeachTime(sign.getTeachTime()+"");
            response.setPunchCard(punchCard);
        }catch (Exception ex){
            ex.printStackTrace();
            response.setSuccess("failed");
            response.setMessage("failed");
            LOG.warn("Failed",ex);
        }
        return G.toJson(response);
    }

    @RequestMapping(value="/response_sign",method = RequestMethod.POST)
    public String responseSign(@RequestBody String requestJson){
        LOG.info("Recive {}",requestJson);
        ResponseSignRequest responseSignRequest = G.fromJson(requestJson,ResponseSignRequest.class);
        ResponseSignResponse responseSignResponse  = new ResponseSignResponse();
        String session = responseSignRequest.getSession();
        if(session == null){
           try{
               RequestSessionKeyResponse requestSessionKeyResponse = requestSessionKey.request(responseSignRequest.getCode());
                session = requestSessionKeyResponse.getOpenid();
           }catch (IOException ex){
               ex.printStackTrace();
           }
        }
        SignStudent signStudent = new SignStudent();
        signStudent.setEvaluate(responseSignRequest.getEvaluate());
        signStudent.setOpenId(session);
        signStudent.setSignId(Integer.parseInt(responseSignRequest.getSignId()));
        signStudent.setStatus(responseSignRequest.getStatus());
        signStudent.setDate(new Date());
        if(responseSignRequest.getPictrues() != null){
            signStudent.setPictrues(G.toJson(responseSignRequest.getPictrues()));
        }
        signStudentRespository.saveAndFlush(signStudent);
        responseSignResponse.setMessage("success");
        responseSignResponse.setSuccess("success");
        return G.toJson(responseSignResponse);
    }

    @RequestMapping("query_sign")
    public String querySign(@RequestBody String requestJson){
        LOG.info("Recive {}",requestJson);
        QuerySignRequest request = G.fromJson(requestJson,QuerySignRequest.class);
        QuerySignResponse response = new QuerySignResponse();
        List<SignStudent> students = signStudentRespository.findBySign(Integer.parseInt(request.getSignId()));
        Sign sign = signRespository.findOne(Integer.parseInt(request.getSignId()));
        PunchCard punchCard = new PunchCard();
        punchCard.setOpenId(sign.getOpenId());
        punchCard.setSignId(""+sign.getSign_id());
        punchCard.setTeachDate(formate.get().format(sign.getTeachDate()));
        punchCard.setTeachTime(sign.getTeachTime()+"");
        response.setPunchCard(punchCard);
        List<QuerySignResponse.Data> datas =students.stream().map(s->{
            UserInfo userInfo = new UserInfo();
            Reply reply = new Reply();
            reply.setEvaluate(s.getEvaluate());
            reply.setOpenId(s.getOpenId());
            reply.setSignId(""+s.getSignId());
            reply.setStatus(s.getStatus());
            if(s.getPictrues()!=null){
                reply.setPictrues(G.fromJson(s.getPictrues(),List.class));
            }
            User user = userRespository.findOne(s.getOpenId());
            userInfo.setAvatarUrl(user.getAvatarUrl());
            userInfo.setNickName(user.getNickName());
            userInfo.setContents(user.getClassContent());
            userInfo.setClassType(user.getClassType());
            userInfo.setRankScore(user.getRankScore());
            userInfo.setRoleType(user.getType()+"");
            userInfo.setStreamId(user.getStreamId());
            QuerySignResponse.Data data= new QuerySignResponse.Data();
            data.setUserInfo(userInfo);
            data.setReply(reply);
            return data;

        }).collect(Collectors.toList());
        response.setDatas(datas);
        return G.toJson(response);
    }


    @RequestMapping("query_sign_by_id")
    public String querySignById(@RequestBody String requestJson){
        Map map = G.fromJson(requestJson,HashMap.class);
        String session = map.get("session").toString();
        Reply reply = new Reply();
        List<SignStudent> students = signStudentRespository.findByOpenId(session);
        if(students.size()!=0){
            reply.setStatus(students.get(0).getStatus());
            reply.setSignId(students.get(0).getSignId()+"");
            reply.setOpenId(session);
            reply.setEvaluate(students.get(0).getEvaluate());
        }
        return G.toJson(reply);
    }

    @RequestMapping(value = "/score",method = RequestMethod.POST)
    public String score(@RequestBody String requestJson){
        Map<String,Object> result = new HashMap<>();
        Map map = G.fromJson(requestJson,Map.class);
        String session = map.get("session") == null ? "":map.get("session").toString();
        TeacherCount teacherCount = teacherCountRespository.findOne(session);
        if(teacherCount !=null){
            result.put("success",true);
            result.put("message","查询成功");
            result.put("allTeachTimes",teacherCount.getSignLong());
            result.put("allCard",teacherCount.getSignTimes());
            String leave;
            if(teacherCount.getSignTimes()>=0 && teacherCount.getSignTimes()<3){
                leave = "助教";
            }else if(teacherCount.getSignTimes()>=3 && teacherCount.getSignTimes()<10){
                leave = "讲师";
            }else if(teacherCount.getSignTimes() >= 10 && teacherCount.getSignTimes()<50){
                leave="副教授";
            }else{
                leave = "教授";
            }
            result.put("leave",leave);
        }else{
            result.put("success",false);
            result.put("message","查询失败");
        }
        return G.toJson(result);
    }

}
