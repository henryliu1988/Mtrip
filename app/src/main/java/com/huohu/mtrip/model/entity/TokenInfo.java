package com.huohu.mtrip.model.entity;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class TokenInfo {

    public TokenInfo() {

    }
    public TokenInfo(TokenInfo info) {
        this.id = info.getId();
        this.mobile = info.getMobile();
        this.avatar = info.getAvatar();
        this.user_nicename = info.getUser_nicename();
        this.birthday = info.getBirthday();
        this.sex = info.getSex();
    }

    private String id = "";
    private String mobile = "";
    private String avatar = "";
    private String user_nicename = "";
    private String birthday = "";
    private String sex = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUser_nicename() {
        return user_nicename;
    }

    public void setUser_nicename(String user_nicename) {
        this.user_nicename = user_nicename;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
