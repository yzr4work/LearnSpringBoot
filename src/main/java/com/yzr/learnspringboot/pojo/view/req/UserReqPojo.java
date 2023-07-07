package com.yzr.learnspringboot.pojo.view.req;

import jakarta.validation.MessageInterpolator;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Negative;

/**
 * 用户展示层请求对象
 * @author yzr
 */
public class UserReqPojo {
    @DecimalMin(value = "1")
    private Long uid;

    public Long getUid () {
        return uid;
    }

    public void setUid (Long uid) {
        this.uid = uid;
    }
}
