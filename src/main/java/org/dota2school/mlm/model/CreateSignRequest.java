package org.dota2school.mlm.model;

import java.util.List;

/**
 * Created by nt on 2017/7/11.
 */
public class CreateSignRequest {

    private String session;
    private String classType;
    private List<String> students;

    private String teachTime;
    private String teachDate;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }


    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
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
