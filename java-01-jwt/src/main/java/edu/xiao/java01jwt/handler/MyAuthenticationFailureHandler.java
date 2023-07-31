package edu.xiao.java01jwt.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.xiao.java01jwt.constant.ResponseCodeEnum;
import edu.xiao.java01jwt.model.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 自定义登录失败处理
 */
@Tag(name = "自定义登录失败处理")
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    @Operation(summary = "登录失败回调")
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        if (exception instanceof BadCredentialsException) {
            String s = new ObjectMapper()
                    .writeValueAsString(Result.error(ResponseCodeEnum.ERROR_PASSWORD_ERROR_500));
            response.getWriter().println(s);
            response.getWriter().flush();
            return;
        }

        String s = new ObjectMapper().writeValueAsString(Result.error("登录失败"));
        response.getWriter().println(s);
        response.getWriter().flush();
    }
}
