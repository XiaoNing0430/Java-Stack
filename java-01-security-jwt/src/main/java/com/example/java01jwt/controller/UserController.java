package com.example.java01jwt.controller;

import com.example.java01jwt.constant.ResponseCodeEnum;
import com.example.java01jwt.model.dto.UserRegisterRequest;
import com.example.java01jwt.model.entity.User;
import com.example.java01jwt.model.vo.Result;
import com.example.java01jwt.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户管理")
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "查询用户", method = "GET")
    @GetMapping
    public Result<?> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Result.ok(authentication);
    }

    @Operation(summary = "用户登录", method = "POST")
    @PostMapping("/login")
    public Result<User> login() {
        return Result.success("登录成功");
    }

    @Operation(summary = "需要登录", method = "GET")
    @GetMapping("/login/needLogin")
    public Result<User> needLogin() {
        return Result.error(ResponseCodeEnum.UNAUTHORIZED_ERROR_401);
    }

    @Operation(summary = "用户注册", method = "POST")
    @PostMapping("/register")
    public Result<User> register(@Validated @RequestBody UserRegisterRequest userRegisterRequest) {
        userService.register(userRegisterRequest);
        return Result.success("注册成功");
    }

}
