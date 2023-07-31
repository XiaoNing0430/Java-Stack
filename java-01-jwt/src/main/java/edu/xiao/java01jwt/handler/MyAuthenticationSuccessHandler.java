package edu.xiao.java01jwt.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.xiao.java01jwt.constant.RedisConstants;
import edu.xiao.java01jwt.model.vo.Result;
import edu.xiao.java01jwt.util.JwtTokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 自定义登录成功处理
 */
@Tag(name = "自定义登录成功处理")
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenUtils jwtTokenUtils;

    private final RedisTemplate redisTemplate;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public MyAuthenticationSuccessHandler(JwtTokenUtils jwtTokenUtils, RedisTemplate redisTemplate) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.redisTemplate = redisTemplate;
    }

    @Override
    @Operation(summary = "登录成功的回调")
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        // 生成token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtTokenUtils.generateToken(userDetails);
        // 存放redis
        String key = RedisConstants.TOKEN_KEY_PREFIX + token + RedisConstants.SEPARATOR + userDetails.getUsername();
        redisTemplate.opsForValue().set(key, userDetails, 30, TimeUnit.MINUTES);
        Map<String, String> result = new HashMap<>(2);
        result.put("token", token);
        result.put("tokenHead", tokenHead);
        String s = new ObjectMapper().writeValueAsString(Result.ok("登录成功", result));
        response.getWriter().println(s);
        response.getWriter().flush();
    }
}
