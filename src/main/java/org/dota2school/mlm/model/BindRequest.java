package org.dota2school.mlm.model;

/**
 *
 * @author xujq
 * @time 2017-7-11
 *
 */
public class BindRequest {

    //{"nikeName":"xuxue哈","roleType":"1","classType":"初级班","contents":"1号位","rankScore":"2000","steamID":"","session":"oUtn60MyXkjGjhLGD1PL-KaaXmqg"}
    private String session;
    private String roleType;
    private String nikeName;
    private String classType;
    private String steamID;
    private String rankScore;
    private String contents;
    private String className;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getSteamID() {
        return steamID;
    }

    public void setSteamID(String steamID) {
        this.steamID = steamID;
    }

    public String getRankScore() {
        return rankScore;
    }

    public void setRankScore(String rankScore) {
        this.rankScore = rankScore;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
