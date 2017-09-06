package org.dota2school.mlm.model;

/**
 *
 * @author xujq
 * @time 2017-7-11
 */
public class BindResponse {

    private String success;
    private String message;
    private UserInfo userInfo;

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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
