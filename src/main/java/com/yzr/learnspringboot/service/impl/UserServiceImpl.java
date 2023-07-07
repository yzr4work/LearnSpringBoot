package com.yzr.learnspringboot.service.impl;

import com.yzr.learnspringboot.dao.UserMapper;
import com.yzr.learnspringboot.pojo.dto.UserDto;
import com.yzr.learnspringboot.pojo.entity.UserEntity;
import com.yzr.learnspringboot.service.UserService;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * 用户信息逻辑实现类
 * @author yzr
 */
@Service
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
        UserEntity userEntity = userMapper.selectUserByUid(uid);
        if (Objects.isNull(userEntity)){
            return Optional.empty();
        }
        UserDto userDto = new UserDto();
        userDto.setUid(userEntity.getUid());
        userDto.setUserName(userEntity.getUserName());
        userDto.setAge(userEntity.getAge());
        return Optional.of(userDto);
    }
}
