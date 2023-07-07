package com.yzr.learnspringboot.service.impl;

import com.yzr.learnspringboot.dao.UserMapper;
import com.yzr.learnspringboot.pojo.dto.UserDto;
import com.yzr.learnspringboot.pojo.entity.UserEntity;
import com.yzr.learnspringboot.service.UserService;
import com.yzr.learnspringboot.util.UserRedisCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * 用户信息逻辑实现类
 * @author yzr
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl (UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param uid 用户ID
     * @return 查询到的用户信息
     */
    @Override
    public Optional<UserDto> getUserByUid (Long uid) {
        //从缓存中获取
        Optional<UserDto> userFromCache = UserRedisCacheUtil.getUserFromCache(uid);
        //缓存中存在直接返回
        if (userFromCache.isPresent()){
            log.info("用户信息缓存查询不为空 uid : {}", uid);
            return userFromCache;
        }
        UserEntity userEntity = userMapper.selectUserByUid(uid);
        if (Objects.isNull(userEntity)){
            log.info("数据库查询用户信息为空 uid :{}" , uid);
            return Optional.empty();
        }
        UserDto userDto = new UserDto();
        userDto.setUid(userEntity.getUid());
        userDto.setUserName(userEntity.getUserName());
        userDto.setAge(userEntity.getAge());
        //向缓存中添加
        UserRedisCacheUtil.addUserIntoCache(userDto);
        return Optional.of(userDto);
    }

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return 是否添加成功
     */
    @Override
    public boolean addUser (UserDto user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getUserName());
        userEntity.setAge(user.getAge());
        userMapper.addUser(userEntity);
        return userEntity.getUid() > 0L;
    }

    /**
     * 修改用户年纪
     *
     * @param userDto 用户信息
     * @return 是否修改成功
     */
    @Override
    public boolean updateUserAge (UserDto userDto) {
        UserRedisCacheUtil.delUserFromCache(userDto.getUid());
        return userMapper.updateUserAge(userDto.getUid(), userDto.getAge()) == 1;

    }
}
