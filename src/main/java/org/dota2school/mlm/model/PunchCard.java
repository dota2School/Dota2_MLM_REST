package org.dota2school.mlm.model;

import java.util.List;

/**
 *
 * @author xujq
 * @time 2017-7-13
 */
public class PunchCard {

    private String signId;
    private String openId;
    private String teachTime;
    private String teachDate;
    private List<String> pictrues;

    public List<String> getPictrues() {
        return pictrues;
    }

    public void setPictrues(List<String> pictrues) {
        this.pictrues = pictrues;
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

    public String getTeachTime() {
        return teachTime;
    }

    public void setTeachTime(String teachTime) {
        this.teachTime = teachTime;
    }

    public String getTeachDate() {
        return teachDate;
    }

    public void setTeachDate(String teachDate) {
        this.teachDate = teachDate;
    }
}
