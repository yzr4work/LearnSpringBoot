package com.yzr.learnspringboot.pojo.view.req;

import jakarta.validation.constraints.NotNull;

/**
 * demo request pojo
 * @author yzr
 */
public class DemoReqPojo {

    @NotNull(message = "id 不能为null")
    private Integer id;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }
}
