package com.yzr.learnspringboot.pojo.view.resp.global;

/**
 * 统一返回包装code码枚举
 * @author yzr
 */
public enum RespCodeEnum {
    /**
     * 正常
     */
    RIGHT("200","正常"),
    /**
     * 参数错误
     */
    PARAM_ERROR("199","参数错误"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR("130","系统错误"),
    ;
    String code;
    String msg;

    public String getCode () {
        return code;
    }

    public String getMsg () {
        return msg;
    }

    RespCodeEnum (String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
