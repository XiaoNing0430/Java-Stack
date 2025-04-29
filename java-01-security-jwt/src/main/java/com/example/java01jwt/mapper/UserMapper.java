package com.example.java01jwt.mapper;

import com.example.java01jwt.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiaoning
 * @description 针对表【ums_user(用户表)】的数据库操作Mapper
 * @createDate 2023-07-21 20:18:27
 * @Entity edu.xiao.java01jwt.entity.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}