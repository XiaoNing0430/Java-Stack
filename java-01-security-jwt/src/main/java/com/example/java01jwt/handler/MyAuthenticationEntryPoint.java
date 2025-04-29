package com.example.java01jwt.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.java01jwt.constant.ResponseCodeEnum;
import com.example.java01jwt.model.vo.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * 自定义认证失败处理
 */
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * Commences an authentication scheme.
     * <p>
     * <code>ExceptionTranslationFilter</code> will populate the <code>HttpSession</code>
     * attribute named
     * <code>AbstractAuthenticationProcessingFilter.SPRING_SECURITY_SAVED_REQUEST_KEY</code>
     * with the requested target URL before calling this method.
     * <p>
     * Implementations should modify the headers on the <code>ServletResponse</code> as
     * necessary to commence the authentication process.
     *
     * @param request       that resulted in an <code>AuthenticationException</code>
     * @param response      so that the user agent can begin authentication
     * @param authException that caused the invocation
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 设置响应类型
        response.setContentType("application/json;charset=UTF-8");
        // 设置响应状态码
        String json = new ObjectMapper().writeValueAsString(Result.error(ResponseCodeEnum.UNAUTHORIZED_ERROR_401));
        response.getWriter().println(json);
        response.getWriter().flush();
    }
}
