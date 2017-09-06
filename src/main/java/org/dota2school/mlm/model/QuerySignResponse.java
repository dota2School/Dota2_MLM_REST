package org.dota2school.mlm.model;

import java.util.List;

/**
 * Created by nt on 2017/7/11.
 */
public class QuerySignResponse {

    private String success;
    private String message;
    private PunchCard punchCard;
    private List<Data> datas;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Data> getDatas() {
        return datas;
    }

    public PunchCard getPunchCard() {
        return punchCard;
    }

    public void setPunchCard(PunchCard punchCard) {
        this.punchCard = punchCard;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    public static class Data{
        private Reply reply;
        private UserInfo userInfo;


        public Reply getReply() {
            return reply;
        }

        public void setReply(Reply reply) {
            this.reply = reply;
        }

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }
    }

}
