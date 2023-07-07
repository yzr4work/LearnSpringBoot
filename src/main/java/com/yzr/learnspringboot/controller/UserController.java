package com.yzr.learnspringboot.controller;

import com.yzr.learnspringboot.pojo.dto.UserDto;
import com.yzr.learnspringboot.pojo.view.req.UserReqPojo;
import com.yzr.learnspringboot.pojo.view.resp.UserRespPojo;
import com.yzr.learnspringboot.pojo.view.resp.global.Response;
import com.yzr.learnspringboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

/**
 * 用户展示控制层
 * @author yzr
 */
@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }
    @PostMapping("getUserByUid")
    public Response getUserByUid(@RequestBody @Validated UserReqPojo userReqPojo){
        log.info("getUserByUid param {}" , userReqPojo.getUid());
        Optional<UserDto> optionalUser = userService.getUserByUid(userReqPojo.getUid());
        if (optionalUser.isEmpty()){
            log.info("getUserByUid resp empty");
            return Response.emptyResp();
        }
        UserDto userDto = optionalUser.get();
        UserRespPojo userRespPojo = new UserRespPojo();
        userRespPojo.setAge(userDto.getAge());
        userRespPojo.setUserName(userDto.getUserName());
        log.info("getUserByUid resp userName : {}, age : {}",userRespPojo.getUserName(), userRespPojo.getAge());
        return Response.success(userRespPojo);
    }
}
