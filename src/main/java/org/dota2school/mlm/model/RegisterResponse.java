package org.dota2school.mlm.model;

/**
 *
 * @author xujq
 * @time 2017-7-11
 */
public class RegisterResponse {

    private String session;
    private String success;
    private String message;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

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
}
