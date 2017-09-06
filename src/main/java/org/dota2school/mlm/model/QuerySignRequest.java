package org.dota2school.mlm.model;

/**
 * Created by nt on 2017/7/11.
 */
public class QuerySignRequest {

    private String session;
    private String signId;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }
}
