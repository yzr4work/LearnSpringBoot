package com.yzr.learnspringboot.util;

import com.yzr.learnspringboot.pojo.dto.UserDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 用户redis缓存工具类
 * hash结构 用户信息
 * @author yzr
 */
@Component
public class UserRedisCacheUtil {
    /**
     * 用户redis哈希结构key前缀
     */
    private static final String USER_HASH_PREFIX = "user:hash:";

    private static final String USER_HASH_UID_KEY = "uid";
    private static final String USER_HASH_USER_NAME_KEY = "userName";
    private static final String USER_HASH_AGE_KEY = "age";

    private static RedisTemplate redisTemplate;

    public UserRedisCacheUtil (RedisTemplate redisTemplate) {
        UserRedisCacheUtil.redisTemplate = redisTemplate;
    }


    /**
     * 从缓存中获取用户信息
     * @param uid 用户ID
     * @return 用户信息
     */
    public static Optional<UserDto> getUserFromCache(Long uid){
        String key = USER_HASH_PREFIX + uid;
        Map<String,Object> hashMap = redisTemplate.opsForHash().entries(key);
        if (CollectionUtils.isEmpty(hashMap)){
            return Optional.empty();
        }
        UserDto userDto = new UserDto();
        userDto.setUid((Long) hashMap.get(USER_HASH_UID_KEY));
        userDto.setUserName((String) hashMap.get(USER_HASH_USER_NAME_KEY));
        userDto.setAge((Integer)hashMap.get(USER_HASH_AGE_KEY));
        return Optional.of(userDto);
    }

    /**
     * 向缓存中添加用户信息
     * @param user 用户信息
     */
    public static void addUserIntoCache(UserDto user){
        String key = USER_HASH_PREFIX + user.getUid();
        HashMap<String, Object> hashMap = new HashMap<>(3);
        hashMap.put(USER_HASH_UID_KEY, user.getUid());
        hashMap.put(USER_HASH_USER_NAME_KEY,user.getUserName());
        hashMap.put(USER_HASH_AGE_KEY, user.getAge());
        redisTemplate.opsForHash().putAll(key,hashMap);
        redisTemplate.expire(key, 10, TimeUnit.MINUTES);
    }


    /**
     * 删除缓存中用户信息
     * @param uid 用户ID
     */
    public static void delUserFromCache(Long uid){
        String key = USER_HASH_PREFIX + uid;
        redisTemplate.delete(key);
    }

}
