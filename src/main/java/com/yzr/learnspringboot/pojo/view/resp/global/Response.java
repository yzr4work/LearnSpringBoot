package com.yzr.learnspringboot.pojo.view.resp.global;

import java.util.Map;

/**
 * 全局返回对象包装
 * @author yzr
 */
public class Response {
    private String code;
    private String msg;
    private Object data;

    public String getCode () {
        return code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    public String getMsg () {
        return msg;
    }

    public void setMsg (String msg) {
        this.msg = msg;
    }

    public Object getData () {
        return data;
    }

    public void setData (Object data) {
        this.data = data;
    }

   public static Response success (Object data){
       Response response = new Response();
       response.setCode(RespCodeEnum.RIGHT.getCode());
       response.setMsg(RespCodeEnum.RIGHT.getMsg());
       response.setData(data);
       return response;
   }
   public static Response emptyResp(){
       Response response = new Response();
       response.setCode(RespCodeEnum.RIGHT.getCode());
       response.setMsg(RespCodeEnum.RIGHT.getMsg());
       return response;
   }

   public static Response paramNotValidatedResp(Map<String ,String> notValidatedMap){
       Response response = new Response();
       response.setCode(RespCodeEnum.PARAM_ERROR.getCode());
       response.setMsg(RespCodeEnum.PARAM_ERROR.getMsg());
       response.setData(notValidatedMap);
       return response;

   }
}
