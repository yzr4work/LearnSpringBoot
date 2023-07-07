package com.yzr.learnspringboot.controller;

import com.yzr.learnspringboot.pojo.view.req.DemoReqPojo;
import com.yzr.learnspringboot.pojo.view.resp.DemoRespPojo;
import com.yzr.learnspringboot.pojo.view.resp.global.Response;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * demoController
 * @author yzr
 */
@RestController
@RequestMapping("/demo/")
public class DemoController {

    @GetMapping("1")
    public String demoMethod(){
        return "demo";
    }

    @PostMapping("2")
    public Response demoMethod2(){
        DemoRespPojo respPojo = new DemoRespPojo();
        respPojo.setName("acg");
        respPojo.setAge(19);
        return Response.success(respPojo);
    }

    @PostMapping("3")
    public Response demoMethod3(@RequestBody @Validated DemoReqPojo demoReqPojo){
        if (1 != demoReqPojo.getId()){
            return Response.emptyResp();
        }
        DemoRespPojo respPojo = new DemoRespPojo();
        respPojo.setName("acg");
        respPojo.setAge(19);
        return Response.success(respPojo);
    }
}
