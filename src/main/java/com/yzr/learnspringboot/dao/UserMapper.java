package com.yzr.learnspringboot.dao;

import com.yzr.learnspringboot.pojo.entity.UserEntity;
import org.apache.ibatis.annotations.*;

/**
 * 用户mapper
 * @author yzr
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户id查询用户
     * @param uid 用户id
     * @return 查寻到用户信息
     */
    @Select("select uid, user_name ,age from user where uid = #{uid};")
    UserEntity selectUserByUid(@Param("uid") Long uid);

    /**
     * 新增用户
     * @param user 用户信息
     */
    @Insert("insert into user (user_name, age) values ( #{user.userName}, #{user.age});")
    @Options(useGeneratedKeys = true,keyColumn = "user.uid", keyProperty = "uid")
    void addUser(@Param("user") UserEntity user);

    /**
     * 修改用户的年纪
     * @param uid 用户ID
     * @param newAge 要修改的年纪
     * @return 影响行数
     */
    @Update("update user set age = #{age}  where uid = #{uid} limit 1;")
    Integer updateUserAge(@Param("uid") Long uid, @Param("age") Integer newAge);

}
