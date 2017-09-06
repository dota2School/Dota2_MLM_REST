package org.dota2school.mlm.model;

import java.util.List;

/**
 *
 * @author xujq
 * @time 2017-7-13
 */
public class Reply {

    private String status;
    private String evaluate;
    private String signId;
    private String openId;
    private List pictrues;


    public List<String> getPictrues() {
        return pictrues;
    }

    public void setPictrues(List pictrues) {
        this.pictrues = pictrues;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
