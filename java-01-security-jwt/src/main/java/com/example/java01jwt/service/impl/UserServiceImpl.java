package com.example.java01jwt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java01jwt.exception.UsernameExistedException;
import com.example.java01jwt.mapper.UserRoleRelationMapper;
import com.example.java01jwt.model.bo.UserBO;
import com.example.java01jwt.model.dto.UserRegisterRequest;
import com.example.java01jwt.model.entity.Resource;
import com.example.java01jwt.model.entity.User;
import com.example.java01jwt.service.UserService;
import com.example.java01jwt.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author xiaoning
* @description 针对表【ums_user(用户表)】的数据库操作Service实现
* @createDate 2023-07-21 20:18:27
*/
@AllArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    private final UserMapper userMapper;

    private final UserRoleRelationMapper userRoleRelationMapper;

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 查询权限列表
        List<Resource> resources = userRoleRelationMapper.listResourceByUserId(user.getId());

        Set<String> list = resources.stream().map(Resource::getUrl).collect(Collectors.toSet());

        return new UserBO(user, list);
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        return this.query().eq("username", username).one();
    }

    /**
     * 注册
     *
     * @param userRegisterRequest
     */
    @Override
    public void register(UserRegisterRequest userRegisterRequest) {
        User user = getUserByUsername(userRegisterRequest.getUsername());
        if (user != null) {
            throw new UsernameExistedException("用户名已存在");
        }
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodePassword = passwordEncoder.encode(userRegisterRequest.getPassword());
        user = new User();
        user.setUsername(userRegisterRequest.getUsername());
        user.setPassword(encodePassword);
        user.setIcon(userRegisterRequest.getIcon());
        user.setEmail(userRegisterRequest.getEmail());
        user.setStatus(1);

        this.save(user);
    }
}




