package com.example.java01jwt.service;

import com.example.java01jwt.model.dto.UserRegisterRequest;
import com.example.java01jwt.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author xiaoning
 * @description 针对表【ums_user(用户表)】的数据库操作Service
 * @createDate 2023-07-21 20:18:27
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 注册
     *
     * @param userRegisterRequest
     */
    void register(UserRegisterRequest userRegisterRequest);
}
