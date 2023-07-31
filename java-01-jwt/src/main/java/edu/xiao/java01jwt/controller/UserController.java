package edu.xiao.java01jwt.controller;

import edu.xiao.java01jwt.constant.ResponseCodeEnum;
import edu.xiao.java01jwt.model.entity.User;
import edu.xiao.java01jwt.model.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户管理")
@RestController
@RequestMapping("/api/user")
public class UserController {

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

}
