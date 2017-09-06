package org.dota2school.mlm.model;

/**
 *
 * @author xujq
 * @time 2017-7-11
 */
public class RegisterRequest {
    private String iv;
    private String code;
    private String encryptedData;


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
}
