package com.yzr.learnspringboot.pojo.view.resp;

/**
 * 用户展示层响应返回对象
 * @author yzr
 */
public class UserRespPojo {
    private String userName;
    private Integer age;

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
