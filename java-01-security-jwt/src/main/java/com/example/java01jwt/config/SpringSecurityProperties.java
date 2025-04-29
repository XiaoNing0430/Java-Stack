package com.example.java01jwt.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Set;

@Data
@Validated
@ConfigurationProperties(prefix = "security")
public class SpringSecurityProperties {

    /**
     * 不需要认证路径
     */
    private Set<String> ignoreUrl;

    /**
     * 登录地址
     */
    @NotEmpty(message = "登录请求地址不能为空")
    private String loginProcessingUrl;

}
