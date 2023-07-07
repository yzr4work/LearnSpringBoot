package com.yzr.learnspringboot.pojo.entity;

/**
 * 用户数据库实体
 * @author yzr
 */
public class UserEntity {
    private Long uid;
    private String userName;
    private Integer age;

    public Long getUid () {
        return uid;
    }

    public void setUid (Long uid) {
        this.uid = uid;
    }

    public String getUserName () {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public Integer getAge () {
        return age;
    }

    public void setAge (Integer age) {
        this.age = age;
    }
}
