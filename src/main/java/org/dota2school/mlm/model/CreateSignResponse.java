package org.dota2school.mlm.model;

/**
 * Created by nt on 2017/7/11.
 */
public class CreateSignResponse {

    private String success;
    private String message;
    private PunchCard punchCard;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public PunchCard getPunchCard() {
        return punchCard;
    }

    public void setPunchCard(PunchCard punchCard) {
        this.punchCard = punchCard;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
