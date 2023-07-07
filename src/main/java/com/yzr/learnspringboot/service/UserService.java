package com.yzr.learnspringboot.service;

import com.yzr.learnspringboot.pojo.dto.UserDto;

import java.util.Optional;

/**
 * 用户逻辑层功能定义接口
 * @author yzr
 */
public interface UserService {
    /**
     * 根据用户ID获取用户信息
     * @param uid 用户ID
     * @return 查询到的用户信息
     */
    Optional<UserDto> getUserByUid(Long uid);
}
