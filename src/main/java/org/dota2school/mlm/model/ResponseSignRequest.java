package org.dota2school.mlm.model;

import java.util.List;

/**
 *
 * @author xujq
 * @time 2017-7-11
 */
public class ResponseSignRequest {

    private String session;
    private String iv;
    private String code;
    private String encryptedData;

    private String signId;
    private String status;
    private String evaluate;
    private List<String> pictrues;


    public List<String> getPictrues() {
        return pictrues;
    }

    public void setPictrues(List<String> pictrues) {
        this.pictrues = pictrues;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
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
}
